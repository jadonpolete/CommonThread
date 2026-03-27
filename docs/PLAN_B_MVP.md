# Plan B: MVP (Model-View-Presenter) Implementation

## Overview
This plan implements the Registration Page following the MVP architecture pattern as an alternative approach. MVP offers different benefits compared to MVVM, particularly in terms of presenter flexibility and explicit view contracts.

## Architecture Layers

### 1. Model Layer
**File:** `model/User.java` and `repository/AuthRepository.java`

The Model represents user data and handles business logic.

Responsibilities:

Store user data
Handle registration logic
Manage data persistence
Abstract data sources
Components:

User POJO with validation
AuthRepository for data operations


### 2. View Layer
**File:** `res/layout/activity_registration.xml` and `view/RegistrationActivity.java`

The View is responsible for UI presentation only (dumb view pattern).

Responsibilities:

Display registration form
Capture user input and pass to Presenter
Update UI based on Presenter instructions
Display validation messages
Display success messages
Key Responsibility:

No business logic
No validation logic
Follows Presenter commands
Interface Contract (RegistrationView):

showFullNameError(String message)
showEmailError(String message)
showPasswordError(String message)
showConfirmPasswordError(String message)
showSuccessMessage(String message)
clearErrors()
setLoadingState(boolean)
getFullName(): String
getEmail(): String
getPassword(): String
getConfirmPassword(): String


### 3. Presenter Layer
**File:** `presenter/RegistrationPresenter.java`

The Presenter acts as the "middleman" between View and Model, containing all presentation logic.

Responsibilities:

Implement view logic and state management
Perform input validation
Coordinate View and Model interactions
Handle user actions from View
Determine what to display on View
Key Methods:

onRegisterClick()
validateInputs(): boolean
onRegistrationSuccess()
onRegistrationFailure(String error)
attachView(RegistrationView)
detachView()


### 4. Repository Layer
**File:** `repository/AuthRepository.java`

The Repository handles all data operations.

Responsibilities:

Register user with backend
Validate data existence
Manage authentication state
Key Methods:

registerUser(User user): void (with callbacks)
checkEmailExists(String email): boolean


## Implementation Steps

### Step 1: Define the User Model
- Create `User.java` with properties: fullName, email, password, confirmPassword
- Add constructor and getter/setter methods
- Keep model simple and focused on data

### Step 2: Create View Interface
- Define `RegistrationView.java` interface
- List all methods View must implement:
  - Error display methods for each field
  - Success message method
  - Input getter methods
  - Loading state methods
- This creates a clear contract between View and Presenter

### Step 3: Create Repository Layer
- Create/Update `AuthRepository.java`
- Implement `registerUser()` with callback pattern
- Add utility methods for data validation
- Setup callback listeners for async operations

### Step 4: Create Presenter
- Create `RegistrationPresenter.java`
- Implement `RegistrationView` interface
- Implement all validation logic:
  - Full Name validation
  - Email format validation
  - Password validation (6+ characters)
  - Password confirmation matching
- Implement error handling
- Coordinate calls between View and Repository
- Manage presenter lifecycle (attachView/detachView)

### Step 5: Create XML Layout
- Create `activity_registration.xml`
- Add EditText fields for all required inputs
- Add Register button
- Add error and success message TextViews
- Apply Material Design principles

### Step 6: Create Activity (View Implementation)
- Create `RegistrationActivity.java` implementing `RegistrationView`
- Initialize Presenter in onCreate()
- Implement all interface methods
- Handle button clicks and pass to Presenter
- Update UI based on Presenter instructions
- Manage lifecycle (attachView in onResume, detachView in onPause)

### Step 7: Integration
- Connect Activity to Presenter
- Wire up button click listeners to Presenter methods
- Implement all RegistrationView interface methods
- Setup repository callbacks

## Validation Rules Implementation

Validation Logic (Presenter):

Full Name:

if (fullName.trim().isEmpty()) → view.showFullNameError("Full Name is required")
Email:

if (email.isEmpty()) → view.showEmailError("Email is required")
if (!isValidEmailFormat(email)) → view.showEmailError("Invalid email format")
if (repository.checkEmailExists(email)) → view.showEmailError("Email already registered")
Password:

if (password.isEmpty()) → view.showPasswordError("Password is required")
if (password.length() < 6) → view.showPasswordError("Password must be at least 6 characters")
Confirm Password:

if (!password.equals(confirmPassword)) → view.showConfirmPasswordError("Passwords do not match")
All Valid:

repository.registerUser(user)
view.showSuccessMessage("Registration successful!")

## Data Flow

User Input (Activity/View) ↓ Activity captures input and calls Presenter.onRegisterClick() ↓ Presenter validates all inputs ↓ If invalid → Presenter calls view.show*Error() methods ↓ Activity updates UI with error messages ↓ If valid → Presenter calls repository.registerUser() ↓ Repository processes registration asynchronously ↓ Repository callback triggered ↓ Presenter handles success/failure ↓ Presenter instructs View what to display ↓ Activity updates UI accordingly

## Class Structure

RegistrationView (Interface) ↑ | RegistrationActivity (View Implementation)

RegistrationPresenter | ├── implements RegistrationView callbacks ├── holds reference to RegistrationView └── holds reference to AuthRepository

AuthRepository | └── handles data operations

User (Model) | └── POJO with user data


## Testing Strategy

### Unit Tests
- Test Presenter validation logic in isolation
- Test Presenter interactions with mock View and Repository
- Test Repository methods
- Test User model

### Instrumentation Tests
- Test Activity as View implementation
- Test presenter lifecycle (attach/detach)
- Test UI updates through Presenter

### Manual Tests
- Test valid registration
- Test each validation error
- Test success message
- Test UI responsiveness

## Advantages of MVP

✓ Very explicit separation of concerns
✓ View is completely dumb (easy to understand)
✓ Easy to test Presenter logic with mock View
✓ Clear contract between View and Presenter (Interface)
✓ Flexible and straightforward
✓ Easier for developers new to architecture patterns

## Disadvantages of MVP

✗ More boilerplate code (interface definitions, callback methods)
✗ Manual lifecycle management (attach/detach)
✗ Presenter can become large if not properly modularized
✗ No built-in lifecycle awareness (unlike MVVM with LiveData)

## Checklist

- [ ] Create `RegistrationView.java` interface
- [ ] Create `model/User.java`
- [ ] Create/Update `repository/AuthRepository.java`
- [ ] Create `presenter/RegistrationPresenter.java`
- [ ] Create `res/layout/activity_registration.xml`
- [ ] Create `view/RegistrationActivity.java` (implement RegistrationView)
- [ ] Implement all validation rules in Presenter
- [ ] Implement all RegistrationView interface methods in Activity
- [ ] Setup presenter lifecycle management
- [ ] Implement success/error message display
- [ ] Write unit tests for Presenter
- [ ] Write unit tests for Repository
- [ ] Write instrumentation tests for Activity
- [ ] Test all validation scenarios
- [ ] Test success scenario
- [ ] Code review for MVP compliance
- [ ] Commit changes with descriptive messages

## Expected Outcome

A fully functional Registration Page that:
- Follows MVP architecture pattern
- Has clear separation between View and Presenter
- Implements explicit view interface contract
- Validates all user inputs with appropriate error messages
- Displays success confirmation
- Is highly testable with mock objects
- Is easy to maintain and understand
- Integrates with CommonThread application
