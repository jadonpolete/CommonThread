# Plan A: MVVM (Model-View-ViewModel) Implementation

## Overview
This plan implements the Registration Page following the MVVM architecture pattern, which is the primary requirement for the CommonThread Android application.

## Architecture Layers

### 1. Model Layer
**File:** `model/User.java`

The Model represents user data with validation logic.

Responsibilities:

Store user data (fullName, email, password)
Provide data getters and setters
Serve as data transfer object (DTO)
Key Components:

fullName: String
email: String
password: String
confirmPassword: String

### 2. View Layer
**File:** `res/layout/activity_registration.xml` and `view/RegistrationActivity.java`

The View is responsible for UI presentation and user interaction.

Responsibilities:

Display registration form with input fields
Capture user input
Observe ViewModel state and update UI accordingly
Display validation error messages
Display success messages

UI Components:

EditText for Full Name
EditText for Email
EditText for Password
EditText for Confirm Password
Button for Register action
TextView for error/success messages

### 3. ViewModel Layer
**File:** `viewmodel/RegistrationViewModel.java`

The ViewModel contains all presentation logic and state management.

Responsibilities:

Hold registration form state (LiveData)
Perform input validation
Manage registration flow
Generate appropriate messages (success/error)
Communicate with Repository

Key LiveData Objects:

registrationState (UI state: IDLE, LOADING, SUCCESS, ERROR)
errorMessage (validation or error messages)
successMessage (registration success confirmation)
### 4. Repository Layer
**File:** `repository/AuthRepository.java`

The Repository handles all data operations and backend communication.

Responsibilities:

Abstract data sources (API, database, shared preferences)
Handle user registration logic
Manage authentication state
Provide data to ViewModel

Key Methods:

registerUser(User user): LiveData<RegistrationResponse>
validateEmail(String email): boolean
isUserExists(String email): boolean

## Implementation Steps

### Step 1: Define the User Model
- Create `User.java` with properties: fullName, email, password, confirmPassword
- Add constructor and getter/setter methods
- Consider adding password encoding/hashing logic

### Step 2: Create Repository Layer
- Create or update `AuthRepository.java`
- Implement `registerUser()` method
- Add helper validation methods
- Setup LiveData for async operations

### Step 3: Create ViewModel
- Create `RegistrationViewModel.java` extending AndroidViewModel
- Define LiveData for registration state
- Implement validation logic:
  - Full Name not empty
  - Email format validation
  - Password length validation (minimum 6 characters)
  - Password match confirmation
- Implement `register()` method that coordinates validation and repository call
- Handle error states and messages

### Step 4: Create XML Layout
- Create `activity_registration.xml`
- Add EditText fields for all required inputs
- Add Register button
- Add TextViews for error/success messages
- Apply Material Design principles
- Consider proper spacing and accessibility

### Step 5: Create Activity
- Create `RegistrationActivity.java` extending AppCompatActivity
- Initialize ViewModel using ViewModelProvider
- Bind UI elements
- Observe ViewModel LiveData objects
- Update UI when state changes
- Handle button clicks

### Step 6: Integration
- Connect Activity to ViewModel
- Set up observers for all LiveData objects
- Implement error message display logic
- Implement success message display logic

## Validation Rules Implementation

Full Name Validation:

if (fullName.isEmpty()) → error: "Full Name is required"
Email Validation:

if (email.isEmpty()) → error: "Email is required"
if (!isValidEmailFormat(email)) → error: "Invalid email format"
if (userExists(email)) → error: "Email already registered"
Password Validation:

if (password.isEmpty()) → error: "Password is required"
if (password.length() < 6) → error: "Password must be at least 6 characters"
Confirm Password Validation:

if (!password.equals(confirmPassword)) → error: "Passwords do not match"
All Valid:

Display: "Registration successful! Welcome to CommonThread"

## Data Flow

User Input (Activity) ↓ RegistrationActivity observes user actions ↓ Call RegistrationViewModel.register() ↓ ViewModel validates input (Model layer) ↓ If invalid → update errorMessage LiveData ↓ If valid → call AuthRepository.registerUser() ↓ Repository processes registration (async) ↓ LiveData updated with result ↓ Activity observes and updates UI


## Testing Strategy

### Unit Tests
- Test validation methods in ViewModel
- Test Repository methods with mock data
- Test User model creation and properties

### Instrumentation Tests
- Test Activity lifecycle and ViewModel binding
- Test LiveData observer updates
- Test UI element visibility and content

### Manual Tests
- Test valid registration scenario
- Test each validation error scenario
- Test success message display
- Test UI responsiveness

## Advantages of MVVM

✓ Clear separation of concerns
✓ Testable code (ViewModel logic is independent of Android framework)
✓ Reusable components
✓ LiveData handles lifecycle-aware state management
✓ Easy to maintain and scale
✓ Aligns with Android architecture recommendations

## Checklist

- [ ] Create `model/User.java`
- [ ] Create/Update `repository/AuthRepository.java`
- [ ] Create `viewmodel/RegistrationViewModel.java`
- [ ] Create `res/layout/activity_registration.xml`
- [ ] Create `view/RegistrationActivity.java`
- [ ] Implement all validation rules
- [ ] Setup LiveData observers in Activity
- [ ] Implement error message display
- [ ] Implement success message display
- [ ] Write unit tests for ViewModel
- [ ] Write instrumentation tests for Activity
- [ ] Test all validation scenarios
- [ ] Test success scenario
- [ ] Code review for MVVM compliance
- [ ] Commit changes with descriptive messages

## Expected Outcome

A fully functional Registration Page that:
- Follows MVVM architecture strictly
- Validates all user inputs with appropriate error messages
- Displays success confirmation
- Is testable and maintainable
- Integrates seamlessly with existing CommonThread architecture
- Provides good user experience with clear feedback
