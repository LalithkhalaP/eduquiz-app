
📘 MINI PROJECT REPORT

EDUCATIONAL QUIZ APPLICATION

Submitted by: Lalith Khala.P,Nihil Dharsini.B.K,Mathumitha sree.M

Department of Computer Science

INTRODUCTION
The "Educational Quiz Application" is a console-based interactive learning system developed in "Java". The goal of this project is to enhance learning by providing:

Engaging quizzes
Multiple subjects
User accounts with level progression
Admin controls for managing questions
Gamification features (badges, scores, progress)
This application provides both students and teachers a simple platform to practice, manage, and evaluate knowledge.

OBJECTIVE OF THE PROJECT
The main objectives are:

Create a user-friendly quiz platform

Allow users to register/login

Provide multiple subjects with categorized questions

Track user progress, scores, levels, badges

Allow admin to add, update, delete questions

Provide interactive UI with console-based animations

Promote gamified learning

EXISTING SYSTEM

Many quiz systems exist but:

Most require internet
Many do not support console-based offline use
Lack personalization
No gamification or user progress
Admin cannot easily modify questions
PROPOSED SYSTEM
The proposed system:

✅ Works offline ✅ Console-based (simple for beginners) ✅ User accounts with username, password, avatar ✅ Leaderboard + scores ✅ Level tracking ✅ Admin panel for editing questions ✅ Clean UI with ASCII-art borders ✅ Hints and 50-50 lifelines

This improves usability and makes learning enjoyable.

SOFTWARE REQUIREMENTS
Language : Java
IDE : VS Code / IntelliJ / Eclipse
JDK Version : JDK 17 or above
Operating System : Windows / Linux / Mac
SYSTEM ARCHITECTURE
6.1 Modules

✅ User Module

User Login / Signup
Select avatar
Start quiz
View dashboard
Track score, level, badges
View achievements
Leaderboard
✅ Admin Module

Add questions
View/Edit questions
Delete questions
View all users
View user statistics
✅ Quiz Module

Subjects: Math, Geography, Science, English, Computer Science
Difficulty levels
Timer system
Hint and 50-50 lifelines
Score calculation
Results summary
SYSTEM DESIGN
7.1 Use Case Diagram (Textual Description)

Actor 1: User
Login
Sign Up
Take Quiz
View Profile
View Achievements
View Progress
Logout
Actor 2: Admin
Add Question
Edit Question
Delete Question
View Users
IMPLEMENTATION DETAILS
This project is implemented in "Java OOP concepts":

✅ Classes Used

User
Admin
Question
QuizManager
Leaderboard
FileManager (for data storage)
UIRenderer (prints UI boxes, menus)
Main class
PROGRAM OUTPUT (FROM EXECUTION)
A sample execution output is recorded in the provided output.txt file. It includes the following:

✅ Home screen ✅ Login selection ✅ User creation ✅ Dashboard ✅ Subject selection ✅ Quiz questions ✅ Correct/Wrong responses ✅ Quiz results ✅ Leaderboard ✅ Profile view ✅ Admin login ✅ Admin dashboard ✅ Add / Edit / Delete questions ✅ Confirmation messages

(Full output displayed in Appendix)

SAMPLE OUTPUT SCREENS
Below are examples of the UI:

✅ Welcome Screen

══════════════════════════════════════════════════ EDUCATIONAL QUIZ APPLICATION ══════════════════════════════════════════════════

✅ Dashboard

🎓 John Doe | Level: 1 | Score: 0 | Badges: 0

✅ Quiz Question

What is 15 + 28?

43
42
44
41
✅ Results

🎯 Score: 7/10 📊 Accuracy: 70.0%

TEST CASES
Test Case	Input	Expected Output
User login	Correct credentials	Login success
Add question	Admin panel	Question saved
Wrong password	Incorrect	Error message
Quiz answer	Correct	+10 score
Delete question	Confirm	Question removed
ADVANTAGES OF THE SYSTEM
✅ Offline learning ✅ Easy to use ✅ Gamified interface ✅ Admin control ✅ Multiple subject support ✅ Lightweight and fast

LIMITATIONS
Console only (no graphics)
Cannot store images
Basic file-based storage
FUTURE ENHANCEMENTS
Convert to JavaFX GUI
Add animations, audio
Cloud storage for users
Multiplayer quiz battle
Mobile app version
CONCLUSION
The "Educational Quiz Application" is a fully functional Java mini project that demonstrates:

Java programming
OOP concepts
File handling
Menu-driven UI
User/Admin role systems
The system helps students learn and practice in an interactive manner while giving admins full control over question managemen
