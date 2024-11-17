## Hostel 🏨 Management System 

🏠Welcome to

A the comprehensive Hostel  ManagementHost Systemel! Management This Java System**-based project designed provides to a efficiently comprehensive manage student solution for and staff managing hostel operations operations within, a including hostel user environment. authentication This, room system supports assignments, functionalities like attendance tracking student, login and, complaint handling. Designed supervisor management, complaint for handling both, students attendance and tracking supervisors,, feedback circulation the system streamlines administrative processes and enhances user experience, and more.

.

---

## Features ✨

---

## *Student Login 🚀 Features*

### User Management
- **Secure Authentication**: Login system with username and password for students.
- **Student Login**: Provides access to personal information, attendance records, and room details.
- **Supervisor Login**: OTP-based password recovery via email for hostel supervisors to securely manage data.

### Supervisor Panel
- **Forgot Password**: OTP-based recovery sent to registered emails.
- **Student Management**: Manage student profiles, assign rooms, and handle feedback.
- **Attendance and Complaints**: Mark and view attendance, manage complaints, and review student profiles.
- **Feedback System**: Circulate feedback forms, analyze sentiment, and take administrative action.

### Dynamic Functionalities
- **Home Pass Management**: Request and manage home passes for students.

---

## Supervisor-Centric Features

### Email Notifications
- **Automated Notifications**: Send updates for credentials, complaints, and feedback status.
- **Room Allocation**: Assign or reallocate rooms to students.
- **Attendance Management**: Mark and view attendance records.
- **Feedback and Complaint Resolution**: Analyze feedback and respond to complaints.
- **CSV Integration**: Store and retrieve data using CSV files for scalability and portability.

---

## System Highlights 🧩

### CSV-Based Data Storage
- **User Authentication**: Secure login and password recovery.
- **Email Notifications**: Important updates for students and supervisors.
- **OTP-Based Verification**: Secure actions for sensitive operations like password reset.

### Student Management 🛠️

- **Profile Management**: View and update student profiles.
- **Room Assignments**: Assign or reassign rooms.
- **Attendance Tracking**: Mark and view attendance records.

### Technology Stack
- **Language**: Java
- **Data Storage**: CSV files
- **Libraries Used**:
    - **JavaMail**: For email notifications.
    - **CSV Handling Utilities**: For data management.

### Additional Functionalities
- **Feedback System**: Circulate feedback forms and analyze input.
- **Sentiment Analysis**: Analyze student feedback for insights.




# Complaint Handling 📦

## Project Structure

The project is organized as follows:

src/ ├── User/ │ ├── UserInfo.java │ ├── StudentLogin.java │ └── StudentCenter.java └── Supervisor/ ├── SupervisorWork.java ├── HostelSupervisor.java ├── ForgetPassword.java └── SpecialFeatures.java


### Features

- **Complaint Management**: Users can register complaints with real-time tracking.
- **Supervisor Functionalities**:
    - Review and resolve complaints.
    - Add or remove students.
    - Allocate and manage hostel rooms.

---

## Technologies Used 🛠️

- **Programming Language**: Java
- **Libraries**:
    - **JavaMail API**: For email functionality.
    - **Scanner**: For handling user inputs.
- **CSV File Operations**:
    - Data is managed using CSV files for simplicity and accessibility.

---

## Installation & Usage 🚀

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/svikraman02/Hostel-Management-System.git
   cd Hostel-Management-System


## ```

## 🔧 Installation & Setup

### 1. Environment Setup
- **Install Java (version 8 or above)**.
- **Clone the Repository**:
  ```bash
  git clone https://github.com/svikraman02/Hostel-Management-System.git


- Ensure CSV files are placed in the appropriate `DataBase` folder structure.

### 2. Compile and Run the Application
- **Using an IDE**: Open the project in your favorite IDE (e.g., IntelliJ IDEA, Eclipse) and run the `Main.java` file.
  - In IntelliJ IDEA, right-click the `Main.java` file and select `Run 'Main'`.
  - In Eclipse, right-click the `Main.java` file and choose `Run As -> Java Application`.

### 3. Database Configuration
- Ensure that the `DataBase` folder contains the correct CSV files before running the application. The system reads and writes to these files for various operations such as marking attendance and managing student data.
  - **Add or Modify CSV Files**: If needed, you can create your own CSV files following the structure of the existing ones.
  - **Check File Permissions**: Ensure that the application has the correct permissions to read and write files in the `DataBase` directory.
  - **Email Configuration**: To enable email functionality for notifications or feedback, please enter your **email ID** and **password generated by Google SMTP** in the `Mail.java` file located in the `Email` folder.
    - **Get Google SMTP Password**:
    - To use Google's SMTP server, you need to enable **Less Secure Apps** or generate an **App Password** if you have 2-Step Verification enabled.
    - **Steps for 2-Step Verification users**:
      1. Go to your Google Account settings: [Google Account](https://myaccount.google.com/).
      2. Under the **Security** tab, scroll to **App passwords**.
      3. Sign in with your Google credentials if prompted.
      4. Select **Mail** as the app and **Windows Computer** (or any device) as the device.
      5. Click **Generate** and copy the 16-character password that appears.
      6. Enter this generated password in the `Mail.java` file where the email password is needed.
    - **For users without 2-Step Verification**:
      1. Enable Less Secure Apps on your Google account by visiting this [link](https://myaccount.google.com/lesssecureapps) and turning the setting ON.
      2. Use your regular Google account password in the `Mail.java` file.

  - **Add JAR Files to Dependencies**: Please add the following JAR files to your IDE dependencies for email functionality:
    - `activation-1.1.1.jar`
    - `javax.mail.jar`

### 4. User Authentication and Access
- **Supervisor Login**: The system supports supervisor login using predefined credentials. The supervisor has full access to manage student data, attendance, and feedback.
- **Resident Login**: Students can log in with their student IDs and view/edit their personal information, attendance, room details, and submit feedback or complaints.

### 5. Interacting with the System
- **Supervisors**:
  - Mark daily attendance.
  - Search for students by name, ID, or room number.
  - Edit student details.
  - Circulate feedback to residents.
- **Residents (Students)**:
  - View and edit personal details (name, room, etc.).
  - Request home passes.
  - Raise complaints.
  - View attendance and feedback.

### 6. Troubleshooting
- **File Not Found**: If you encounter a "File Not Found" error, ensure that the CSV files are correctly placed in the `DataBase` folder.
- **Permissions Error**: Make sure your IDE or terminal has the necessary permissions to access and modify files in the project directory.



---


---

## 📚 Dependencies

- **JavaMail Library**: Ensure `javax.mail` is included in the classpath for email functionalities.
- **CSV Files**: Update paths for `DataBase/` CSV files in the code if needed.

---

## 🤝 How to Contribute

1. **Fork the repository**.
2. **Create a new branch**:
3. **Commit your changes**:
4. **Push to the branch**:

5. **Open a pull request**.

---

## 🛡️ Security

This project uses OTP-based verification for password resets and email notifications to ensure secure user actions. Make sure your email credentials are secure and not publicly shared in the code.

---

## 📝 Feedback & Support

For feedback or support, raise an issue on the repository or contact [svvikraman18@gmail.com](mailto:svvikraman18@gmail.com).

---

## 👨‍💻 Author

**VIKRAMAN S**  
**THARUN KUMAR S**  
**VISHNU KUMAR S**  
*Java Developer | Backend Enthusiast*  
Follow me on [GitHub](https://github.com/svikraman02) for more projects!

---

## 📜 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

---

### 🌟 Thank you for using the Hostel Management System! 🌟

For any further questions, please reach out to **[ssngentshostel@gmail.com](mailto:ssngentshostel@gmail.com).**.








