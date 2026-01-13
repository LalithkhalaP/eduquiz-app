// ...existing code...
import java.util.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Main Application Class
class EducationalQuizApp {
    private static Scanner scanner = new Scanner(System.in);
    private static DatabaseManager db = new DatabaseManager();
    private static User currentUser = null;
    private static boolean isDarkMode = false;
    
    public static void main(String[] args) {
        clearScreen();
        showWelcome();
        showLoginSelection();
    }
    
    // ...existing code...
}
    
    private static void showWelcome() {
        printHeader("EDUCATIONAL QUIZ APPLICATION");
        System.out.println("\n" + centerText("Welcome to the Ultimate Learning Platform!"));
        pause(1500);
    }
    
    private static void showLoginSelection() {
        while (true) {
            clearScreen();
            printHeader("LOGIN SELECTION");
            System.out.println("\n1. User Login");
            System.out.println("2. Admin Login");
            System.out.println("3. Exit");
            System.out.print("\nEnter your choice: ");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    showUserLogin();
                    break;
                case "2":
                    showAdminLogin();
                    break;
                case "3":
                    System.out.println("\n✓ Thank you for using Educational Quiz App!");
                    System.exit(0);
                default:
                    System.out.println("\n✗ Invalid choice! Please try again.");
                    pause(1500);
            }
        }
    }
    
    private static void showUserLogin() {
        clearScreen();
        printHeader("USER LOGIN");
        
        System.out.println("\n1. Login");
        System.out.println("2. Sign Up");
        System.out.println("3. Back");
        System.out.print("\nEnter your choice: ");
        
        String choice = scanner.nextLine();
        
        switch (choice) {
            case "1":
                performUserLogin();
                break;
            case "2":
                performUserSignup();
                break;
            case "3":
                return;
            default:
                System.out.println("\n✗ Invalid choice!");
                pause(1500);
                showUserLogin();
        }
    }
    
    private static void performUserLogin() {
        clearScreen();
        printHeader("USER LOGIN");
        
        System.out.print("\nUsername: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        User user = db.loginUser(username, password);
        if (user != null) {
            currentUser = user;
            System.out.println("\n✓ Login successful! Welcome " + user.getName());
            pause(1500);
            showUserDashboard();
        } else {
            System.out.println("\n✗ Invalid credentials!");
            pause(1500);
            showUserLogin();
        }
    }
    
    private static void performUserSignup() {
        clearScreen();
        printHeader("CREATE ACCOUNT");
        
        System.out.print("\nUsername: ");
        String username = scanner.nextLine();
        
        System.out.print("Full Name: ");
        String name = scanner.nextLine();
        
        System.out.print("What do you like?: ");
        String likes = scanner.nextLine();
        
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        System.out.println("\nChoose Avatar:");
        System.out.println("1. 🎓 Graduate  2. 🦊 Fox  3. 🦁 Lion");
        System.out.println("4. 🐼 Panda     5. 🦉 Owl  6. 🚀 Rocket");
        System.out.print("\nChoice (1-6): ");
        
        String[] avatars = {"🎓", "🦊", "🦁", "🐼", "🦉", "🚀"};
        int avatarChoice = getIntInput(1, 6) - 1;
        
        User user = new User(username, name, likes, avatars[avatarChoice]);
        
        if (db.registerUser(user, password)) {
            currentUser = user;
            System.out.println("\n✓ Account created successfully!");
            pause(1500);
            showUserDashboard();
        } else {
            System.out.println("\n✗ Username already exists!");
            pause(1500);
            showUserLogin();
        }
    }
    
    private static void showUserDashboard() {
        while (true) {
            clearScreen();
            printDashboardHeader();
            
            System.out.println("\n╔════════════════════════════════════════════════╗");
            System.out.println("║              DASHBOARD MENU                    ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            
            System.out.println("\n1. 🎯 Start Quiz");
            System.out.println("2. 📚 Choose Subject");
            System.out.println("3. 🏆 View Leaderboard");
            System.out.println("4. 👤 View Profile");
            System.out.println("5. 🏅 View Achievements");
            System.out.println("6. 📊 View Progress");
            System.out.println("7. " + (isDarkMode ? "☀" : "🌙") + " Toggle Theme");
            System.out.println("8. 🚪 Logout");
            
            System.out.print("\nEnter your choice: ");
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    showSubjectSelection();
                    break;
                case "2":
                    showSubjectSelection();
                    break;
                case "3":
                    showLeaderboard();
                    break;
                case "4":
                    showProfile();
                    break;
                case "5":
                    showAchievements();
                    break;
                case "6":
                    showProgress();
                    break;
                case "7":
                    isDarkMode = !isDarkMode;
                    System.out.println("\n✓ Theme changed to " + (isDarkMode ? "Dark" : "Light") + " mode!");
                    pause(1000);
                    break;
                case "8":
                    currentUser = null;
                    return;
                default:
                    System.out.println("\n✗ Invalid choice!");
                    pause(1500);
            }
        }
    }
    
    private static void printDashboardHeader() {
        printHeader("USER DASHBOARD");
        System.out.println("\n" + currentUser.getAvatar() + " " + currentUser.getName() + 
                          " | Level: " + currentUser.getLevel() + 
                          " | Score: " + currentUser.getTotalScore() +
                          " | Badges: " + currentUser.getBadges().size());
        printLine();
    }
    
    private static void showSubjectSelection() {
        clearScreen();
        printHeader("SELECT SUBJECT");
        
        System.out.println("\n1. ➕ Mathematics");
        System.out.println("2. 🌍 Geography");
        System.out.println("3. 🔬 Science");
        System.out.println("4. 📚 English");
        System.out.println("5. 💻 Computer Science");
        System.out.println("6. 🎯 Daily Challenge");
        System.out.println("7. 🔙 Back");
        
        System.out.print("\nEnter your choice: ");
        String choice = scanner.nextLine();
        
        String[] subjects = {"Math", "Geography", "Science", "English", "Computer", "Daily"};
        
        if (choice.matches("[1-6]")) {
            startQuiz(subjects[Integer.parseInt(choice) - 1]);
        } else if (!choice.equals("7")) {
            System.out.println("\n✗ Invalid choice!");
            pause(1500);
            showSubjectSelection();
        }
    }
    
    private static void startQuiz(String subject) {
        List<Question> questions = db.getQuestions(subject, currentUser.getDifficulty());
        
        if (questions.isEmpty()) {
            System.out.println("\n✗ No questions available for " + subject);
            pause(2000);
            return;
        }
        
        QuizSession session = new QuizSession(questions, currentUser, subject);
        conductQuiz(session);
    }
    
    private static void conductQuiz(QuizSession session) {
        while (session.hasNextQuestion()) {
            clearScreen();
            Question q = session.getCurrentQuestion();
            
            // Display question counter and progress
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("║  Question " + (session.getCurrentQuestionIndex() + 1) + 
                             "/" + session.getTotalQuestions() + 
                             " | Time: " + session.getTimeRemaining() + "s" +
                             " | Score: " + session.getScore() + "     ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            
            // Progress bar
            displayProgressBar(session.getCurrentQuestionIndex(), session.getTotalQuestions());
            
            // Display question
            System.out.println("\n" + q.getQuestion());
            System.out.println();
            
            // Display options
            String[] options = q.getOptions();
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }
            
            // Lifelines
            System.out.println("\n💡 Hints: " + session.getHintsRemaining() + 
                             " | 🎲 50:50: " + session.getFiftyFiftyRemaining());
            
            System.out.println("\nOptions: 1-4 (Answer) | 5 (Hint) | 6 (50:50) | 0 (Skip)");
            System.out.print("Your choice: ");
            
            long startTime = System.currentTimeMillis();
            String answer = scanner.nextLine();
            
            // Check timeout (30 seconds)
            if ((System.currentTimeMillis() - startTime) / 1000 > session.getTimeRemaining()) {
                System.out.println("\n⏱ Time's up!");
                session.skipQuestion();
                pause(2000);
                continue;
            }
            
            if (answer.equals("5")) {
                if (session.useHint()) {
                    System.out.println("\n💡 Hint: " + q.getHint());
                    pause(3000);
                    continue;
                } else {
                    System.out.println("\n✗ No hints remaining!");
                    pause(1500);
                    continue;
                }
            } else if (answer.equals("6")) {
                if (session.useFiftyFifty()) {
                    System.out.println("\n🎲 50:50 used! Two incorrect answers removed.");
                    pause(2000);
                    continue;
                } else {
                    System.out.println("\n✗ No 50:50 remaining!");
                    pause(1500);
                    continue;
                }
            } else if (answer.equals("0")) {
                session.skipQuestion();
                System.out.println("\n⏭ Question skipped!");
                pause(1500);
                continue;
            } else if (answer.matches("[1-4]")) {
                int selectedIndex = Integer.parseInt(answer) - 1;
                boolean correct = session.answerQuestion(selectedIndex);
                
                if (correct) {
                    System.out.println("\n✓ Correct! Well done!");
                    displayCorrectAnimation();
                } else {
                    System.out.println("\n✗ Wrong! Correct answer was: " + 
                                     options[q.getCorrectAnswer()]);
                    displayWrongAnimation();
                }
                pause(2500);
            } else {
                System.out.println("\n✗ Invalid input!");
                pause(1500);
                continue;
            }
        }
        
        showQuizResults(session);
    }
    
    private static void displayProgressBar(int current, int total) {
        int barLength = 40;
        int filled = (int) ((double) current / total * barLength);
        
        System.out.print("\nProgress: [");
        for (int i = 0; i < barLength; i++) {
            if (i < filled) {
                System.out.print("█");
            } else {
                System.out.print("░");
            }
        }
        System.out.println("] " + String.format("%.0f%%", (double) current / total * 100));
    }
    
    private static void displayCorrectAnimation() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║         ✓ CORRECT ANSWER!            ║");
        System.out.println("╚══════════════════════════════════════╝");
    }
    
    private static void displayWrongAnimation() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║         ✗ WRONG ANSWER!              ║");
        System.out.println("╚══════════════════════════════════════╝");
    }
    
    private static void showQuizResults(QuizSession session) {
        clearScreen();
        printHeader("QUIZ RESULTS");
        
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║              QUIZ COMPLETE!                    ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        
        System.out.println("\n🎯 Score: " + session.getScore() + "/" + session.getTotalQuestions());
        System.out.println("📊 Accuracy: " + String.format("%.1f%%", session.getAccuracy()));
        
        int earnedPoints = session.getScore() * 10;
        currentUser.addScore(earnedPoints);
        System.out.println("⭐ Points Earned: " + earnedPoints);
        System.out.println("🏆 Total Score: " + currentUser.getTotalScore());
        System.out.println("📈 Level: " + currentUser.getLevel());
        
        // Check for new badges
        List<String> newBadges = checkBadges(session);
        if (!newBadges.isEmpty()) {
            System.out.println("\n🏅 NEW BADGES EARNED:");
            for (String badge : newBadges) {
                System.out.println("   " + badge);
            }
        }
        
        db.saveQuizResult(currentUser, session);
        
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    private static List<String> checkBadges(QuizSession session) {
        List<String> newBadges = new ArrayList<>();
        
        if (currentUser.getQuizzesCompleted() == 1) {
            newBadges.add("🌟 First Quiz Complete");
            currentUser.addBadge("🌟");
        }
        
        if (session.getAccuracy() == 100) {
            newBadges.add("💯 Perfect Score");
            currentUser.addBadge("💯");
        }
        
        if (session.getScore() >= session.getTotalQuestions() * 0.9) {
            newBadges.add("🎯 Accuracy Master");
            currentUser.addBadge("🎯");
        }
        
        if (currentUser.getQuizzesCompleted() >= 10) {
            newBadges.add("🔥 10 Quiz Streak");
            currentUser.addBadge("🔥");
        }
        
        return newBadges;
    }
    
    private static void showLeaderboard() {
        clearScreen();
        printHeader("LEADERBOARD");
        
        List<User> topUsers = db.getTopUsers(10);
        
        System.out.println("\n╔════╦══════════════════════╦═══════╦═══════╗");
        System.out.println("║ #  ║ Name                 ║ Score ║ Level ║");
        System.out.println("╠════╬══════════════════════╬═══════╬═══════╣");
        
        for (int i = 0; i < topUsers.size(); i++) {
            User u = topUsers.get(i);
            System.out.printf("║ %-2d ║ %-20s ║ %-5d ║ %-5d ║%n", 
                            (i + 1), 
                            u.getAvatar() + " " + u.getName().substring(0, Math.min(15, u.getName().length())),
                            u.getTotalScore(),
                            u.getLevel());
        }
        
        System.out.println("╚════╩══════════════════════╩═══════╩═══════╝");
        
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    private static void showProfile() {
        clearScreen();
        printHeader("USER PROFILE");
        
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║              PROFILE DETAILS                   ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        
        System.out.println("\n" + currentUser.getAvatar() + " Username: " + currentUser.getUsername());
        System.out.println("👤 Name: " + currentUser.getName());
        System.out.println("❤️  Likes: " + currentUser.getLikes());
        System.out.println("📊 Level: " + currentUser.getLevel());
        System.out.println("⭐ Total Score: " + currentUser.getTotalScore());
        System.out.println("📝 Quizzes Completed: " + currentUser.getQuizzesCompleted());
        System.out.println("🎯 Difficulty: " + currentUser.getDifficulty());
        
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    private static void showAchievements() {
        clearScreen();
        printHeader("ACHIEVEMENTS");
        
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║              YOUR BADGES                       ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        
        List<String> badges = currentUser.getBadges();
        
        if (badges.isEmpty()) {
            System.out.println("\nNo badges earned yet. Complete quizzes to earn badges!");
        } else {
            System.out.println("\n🏅 Total Badges: " + badges.size());
            System.out.println();
            for (String badge : badges) {
                System.out.println("   " + badge + " " + getBadgeName(badge));
            }
        }
        
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    private static String getBadgeName(String badge) {
        Map<String, String> badgeNames = new HashMap<>();
        badgeNames.put("🌟", "First Quiz Complete");
        badgeNames.put("🔥", "10 Quiz Streak");
        badgeNames.put("💯", "Perfect Score");
        badgeNames.put("🚀", "Speed Demon");
        badgeNames.put("🎯", "Accuracy Master");
        return badgeNames.getOrDefault(badge, "Achievement");
    }
    
    private static void showProgress() {
        clearScreen();
        printHeader("PROGRESS TRACKER");
        
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║            PROGRESS OVERVIEW                   ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        
        List<QuizResult> results = db.getUserResults(currentUser.getUsername());
        
        System.out.println("\n📊 Total Quizzes: " + results.size());
        
        if (!results.isEmpty()) {
            double avgAccuracy = results.stream()
                .mapToDouble(r -> r.accuracy)
                .average()
                .orElse(0.0);
            
            System.out.println("📈 Average Accuracy: " + String.format("%.1f%%", avgAccuracy));
            System.out.println("🏆 Highest Score: " + results.stream()
                .mapToInt(r -> r.score)
                .max()
                .orElse(0));
            
            System.out.println("\n--- Recent Quiz History ---\n");
            
            for (int i = Math.max(0, results.size() - 5); i < results.size(); i++) {
                QuizResult r = results.get(i);
                System.out.println((i + 1) + ". Score: " + r.score + "/" + r.totalQuestions + 
                                 " | Accuracy: " + String.format("%.0f%%", r.accuracy) +
                                 " | " + r.timestamp.format(DateTimeFormatter.ofPattern("MM/dd HH:mm")));
            }
        }
        
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    // ADMIN SECTION
    private static void showAdminLogin() {
        clearScreen();
        printHeader("ADMIN LOGIN");
        
        System.out.print("\nUsername: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        if (username.equals("admin") && password.equals("admin123")) {
            System.out.println("\n✓ Admin login successful!");
            pause(1500);
            showAdminDashboard();
        } else {
            System.out.println("\n✗ Invalid admin credentials!");
            pause(1500);
        }
    }
    
    private static void showAdminDashboard() {
        while (true) {
            clearScreen();
            printHeader("ADMIN DASHBOARD");
            
            System.out.println("\n📊 Statistics:");
            System.out.println("   Users: " + db.getTotalUsers());
            System.out.println("   Questions: " + db.getTotalQuestions());
            System.out.println("   Quizzes Taken: " + db.getTotalQuizzesTaken());
            
            printLine();
            
            System.out.println("\n1. ➕ Add Question");
            System.out.println("2. 📝 View/Edit Questions");
            System.out.println("3. 🗑️  Delete Question");
            System.out.println("4. 👥 View All Users");
            System.out.println("5. 📊 View User Statistics");
            System.out.println("6. 🚪 Logout");
            
            System.out.print("\nEnter your choice: ");
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    addQuestion();
                    break;
                case "2":
                    viewEditQuestions();
                    break;
                case "3":
                    deleteQuestion();
                    break;
                case "4":
                    viewAllUsers();
                    break;
                case "5":
                    viewUserStatistics();
                    break;
                case "6":
                    return;
                default:
                    System.out.println("\n✗ Invalid choice!");
                    pause(1500);
            }
        }
    }
    
    private static void addQuestion() {
        clearScreen();
        printHeader("ADD NEW QUESTION");
        
        System.out.println("\nSelect Subject:");
        System.out.println("1. Math  2. Geography  3. Science  4. English  5. Computer  6. Daily");
        System.out.print("Choice: ");
        String[] subjects = {"Math", "Geography", "Science", "English", "Computer", "Daily"};
        int subChoice = getIntInput(1, 6) - 1;
        
        System.out.println("\nSelect Difficulty:");
        System.out.println("1. Easy  2. Medium  3. Hard");
        System.out.print("Choice: ");
        String[] difficulties = {"Easy", "Medium", "Hard"};
        int diffChoice = getIntInput(1, 3) - 1;
        
        System.out.print("\nEnter Question: ");
        String question = scanner.nextLine();
        
        String[] options = new String[4];
        for (int i = 0; i < 4; i++) {
            System.out.print("Option " + (i + 1) + ": ");
            options[i] = scanner.nextLine();
        }
        
        System.out.print("\nCorrect Answer (1-4): ");
        int correctAnswer = getIntInput(1, 4) - 1;
        
        System.out.print("Hint: ");
        String hint = scanner.nextLine();
        
        Question q = new Question(question, options, correctAnswer, 
                                 subjects[subChoice], difficulties[diffChoice], hint);
        db.addQuestion(q);
        
        System.out.println("\n✓ Question added successfully!");
        pause(2000);
    }
    
    private static void viewEditQuestions() {
        clearScreen();
        printHeader("VIEW/EDIT QUESTIONS");
        
        System.out.println("\nSelect Subject:");
        System.out.println("1. Math  2. Geography  3. Science  4. English  5. Computer  6. Daily  7. All");
        System.out.print("Choice: ");
        String[] subjects = {"Math", "Geography", "Science", "English", "Computer", "Daily", "All"};
        int choice = getIntInput(1, 7) - 1;
        
        List<Question> questions = db.getAllQuestions(subjects[choice]);
        
        if (questions.isEmpty()) {
            System.out.println("\n✗ No questions found!");
            pause(2000);
            return;
        }
        
        System.out.println("\n--- Questions ---\n");
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println((i + 1) + ". [" + q.getSubject() + " - " + q.getDifficulty() + "] " + 
                             q.getQuestion().substring(0, Math.min(50, q.getQuestion().length())) + "...");
        }
        
        System.out.print("\nSelect question to edit (0 to cancel): ");
        int editChoice = getIntInput(0, questions.size());
        
        if (editChoice > 0) {
            editQuestion(questions.get(editChoice - 1));
        }
    }
    
    private static void editQuestion(Question q) {
        clearScreen();
        printHeader("EDIT QUESTION");
        
        System.out.println("\nCurrent Question: " + q.getQuestion());
        System.out.println("\n1. Edit Question Text");
        System.out.println("2. Edit Options");
        System.out.println("3. Change Correct Answer");
        System.out.println("4. Edit Hint");
        System.out.println("5. Cancel");
        
        System.out.print("\nChoice: ");
        String choice = scanner.nextLine();
        
        switch (choice) {
            case "1":
                System.out.print("New Question: ");
                q.setQuestion(scanner.nextLine());
                db.updateQuestion(q);
                System.out.println("\n✓ Question updated!");
                break;
            case "2":
                String[] newOptions = new String[4];
                for (int i = 0; i < 4; i++) {
                    System.out.print("Option " + (i + 1) + ": ");
                    newOptions[i] = scanner.nextLine();
                }
                q.setOptions(newOptions);
                db.updateQuestion(q);
                System.out.println("\n✓ Options updated!");
                break;
            case "3":
                System.out.print("Correct Answer (1-4): ");
                q.setCorrectAnswer(getIntInput(1, 4) - 1);
                db.updateQuestion(q);
                System.out.println("\n✓ Correct answer updated!");
                break;
            case "4":
                System.out.print("New Hint: ");
                q.setHint(scanner.nextLine());
                db.updateQuestion(q);
                System.out.println("\n✓ Hint updated!");
                break;
        }
        
        pause(2000);
    }
    
    private static void deleteQuestion() {
        clearScreen();
        printHeader("DELETE QUESTION");
        
        List<Question> questions = db.getAllQuestions("All");
        
        if (questions.isEmpty()) {
            System.out.println("\n✗ No questions found!");
            pause(2000);
            return;
        }
        
        System.out.println("\n--- Questions ---\n");
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println((i + 1) + ". [" + q.getSubject() + "] " + q.getQuestion().substring(0, Math.min(50, q.getQuestion().length())) + "...");
        }
        
        System.out.print("\nSelect question to delete (0 to cancel): ");
        int choice = getIntInput(0, questions.size());
        
        if (choice > 0) {
            System.out.print("\nAre you sure? (y/n): ");
            if (scanner.nextLine().equalsIgnoreCase("y")) {
                db.deleteQuestion(questions.get(choice - 1).getId());
                System.out.println("\n✓ Question deleted!");
                pause(2000);
            }
        }
    }
    
    private static void viewAllUsers() {
        clearScreen();
        printHeader("ALL USERS");
        
        List<User> users = db.getAllUsers();
        
        System.out.println("\n╔════════════════════════╦═══════╦═══════╦═══════╗");
        System.out.println("║ Name                   ║ Level ║ Score ║ Quiz  ║");
        System.out.println("╠════════════════════════╬═══════╬═══════╬═══════╣");
        
        for (User u : users) {
            System.out.printf("║ %-22s ║ %-5d ║ %-5d ║ %-5d ║%n",
                            u.getAvatar() + " " + u.getName().substring(0, Math.min(18, u.getName().length())),
                            u.getLevel(),
                            u.getTotalScore(),
                            u.getQuizzesCompleted());
        }
        
        System.out.println("╚════════════════════════╩═══════╩═══════╩═══════╝");
        
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    private static void viewUserStatistics() {
        clearScreen();
        printHeader("USER STATISTICS");
        
        List<UserStats> stats = db.getUserStatistics();
        
        System.out.println("\n╔══════════════════╦═══════╦════════╦═════════╗");
        System.out.println("║ Username         ║ Quiz  ║ Score  ║ Avg Acc ║");
        System.out.println("╠══════════════════╬═══════╬════════╬═════════╣");
        
        for (UserStats s : stats) {
            System.out.printf("║ %-16s ║ %-5d ║ %-6d ║ %5.1f%%  ║%n",
                            s.username.substring(0, Math.min(16, s.username.length())),
                            s.quizzesTaken,
                            s.totalScore,
                            s.avgAccuracy);
        }
        
        System.out.println("╚══════════════════╩═══════╩════════╩═════════╝");
        
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    // UTILITY METHODS
    private static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }
    
    private static void printHeader(String title) {
        int width = 50;
        printLine();
        System.out.println(centerText(title));
        printLine();
    }
    
    private static void printLine() {
        System.out.println("══════════════════════════════════════════════════");
    }
    
    private static String centerText(String text) {
        int width = 50;
        int padding = (width - text.length()) / 2;
        return " ".repeat(Math.max(0, padding)) + text;
    }
    
    private static void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private static int getIntInput(int min, int max) {
        while (true) {
            try {
                String input = scanner.nextLine();
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.print("Please enter a number between " + min + " and " + max + ": ");
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter a number: ");
            }
        }
    }
}

// USER CLASS
class User {
    private String username;
    private String name;
    private String likes;
    private String avatar;
    private int totalScore;
    private int level;
    private List<String> badges;
    private int quizzesCompleted;
    private String difficulty;
    
    public User(String username, String name, String likes, String avatar) {
        this.username = username;
        this.name = name;
        this.likes = likes;
        this.avatar = avatar;
        this.totalScore = 0;
        this.level = 1;
        this.badges = new ArrayList<>();
        this.quizzesCompleted = 0;
        this.difficulty = "Easy";
    }
    
    public void addScore(int points) {
        totalScore += points;
        level = (totalScore / 100) + 1;
    }
    
    public void addBadge(String badge) {
        if (!badges.contains(badge)) {
            badges.add(badge);
        }
    }
    
    public void incrementQuizzes() {
        quizzesCompleted++;
    }
    
    // Getters
    public String getUsername() { return username; }
    public String getName() { return name; }
    public String getLikes() { return likes; }
    public String getAvatar() { return avatar; }
    public int getTotalScore() { return totalScore; }
    public int getLevel() { return level; }
    public List<String> getBadges() { return badges; }
    public int getQuizzesCompleted() { return quizzesCompleted; }
    public String getDifficulty() { return difficulty; }
    
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    public void setTotalScore(int score) { this.totalScore = score; }
    public void setLevel(int level) { this.level = level; }
}

// QUESTION CLASS
class Question {
    private static int idCounter = 0;
    private int id;
    private String question;
    private String[] options;
    private int correctAnswer;
    private String subject;
    private String difficulty;
    private String hint;
    
    public Question(String question, String[] options, int correctAnswer, 
                   String subject, String difficulty, String hint) {
        this.id = idCounter++;
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.subject = subject;
        this.difficulty = difficulty;
        this.hint = hint;
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public String getQuestion() { return question; }
    public String[] getOptions() { return options; }
    public int getCorrectAnswer() { return correctAnswer; }
    public String getSubject() { return subject; }
    public String getDifficulty() { return difficulty; }
    public String getHint() { return hint; }
    
    public void setQuestion(String question) { this.question = question; }
    public void setOptions(String[] options) { this.options = options; }
    public void setCorrectAnswer(int correctAnswer) { this.correctAnswer = correctAnswer; }
    public void setHint(String hint) { this.hint = hint; }
}

// QUIZ SESSION CLASS
class QuizSession {
    private List<Question> questions;
    private int currentQuestionIndex;
    private int score;
    private int timeRemaining;
    private int hintsRemaining;
    private int fiftyFiftyRemaining;
    private User user;
    private String subject;
    
    public QuizSession(List<Question> questions, User user, String subject) {
        this.questions = questions;
        this.user = user;
        this.subject = subject;
        this.currentQuestionIndex = 0;
        this.score = 0;
        this.timeRemaining = 30;
        this.hintsRemaining = 3;
        this.fiftyFiftyRemaining = 2;
    }
    
    public Question getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }
    
    public boolean answerQuestion(int answer) {
        boolean correct = getCurrentQuestion().getCorrectAnswer() == answer;
        if (correct) {
            score++;
        }
        currentQuestionIndex++;
        timeRemaining = 30;
        return correct;
    }
    
    public void skipQuestion() {
        currentQuestionIndex++;
        timeRemaining = 30;
    }
    
    public boolean useHint() {
        if (hintsRemaining > 0) {
            hintsRemaining--;
            return true;
        }
        return false;
    }
    
    public boolean useFiftyFifty() {
        if (fiftyFiftyRemaining > 0) {
            fiftyFiftyRemaining--;
            return true;
        }
        return false;
    }
    
    public void decrementTime() {
        if (timeRemaining > 0) {
            timeRemaining--;
        }
    }
    
    public boolean hasNextQuestion() {
        return currentQuestionIndex < questions.size();
    }
    
    public double getAccuracy() {
        return (score * 100.0) / questions.size();
    }
    
    // Getters
    public int getCurrentQuestionIndex() { return currentQuestionIndex; }
    public int getTotalQuestions() { return questions.size(); }
    public int getScore() { return score; }
    public int getTimeRemaining() { return timeRemaining; }
    public int getHintsRemaining() { return hintsRemaining; }
    public int getFiftyFiftyRemaining() { return fiftyFiftyRemaining; }
    public String getSubject() { return subject; }
}

// QUIZ RESULT CLASS
class QuizResult {
    String username;
    int score;
    int totalQuestions;
    double accuracy;
    LocalDateTime timestamp;
    
    public QuizResult(String username, int score, int totalQuestions, double accuracy) {
        this.username = username;
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.accuracy = accuracy;
        this.timestamp = LocalDateTime.now();
    }
}

// USER STATISTICS CLASS
class UserStats {
    String username;
    int quizzesTaken;
    int totalScore;
    double avgAccuracy;
    int level;
    
    public UserStats(String username, int quizzesTaken, int totalScore, 
                    double avgAccuracy, int level) {
        this.username = username;
        this.quizzesTaken = quizzesTaken;
        this.totalScore = totalScore;
        this.avgAccuracy = avgAccuracy;
        this.level = level;
    }
}

// DATABASE MANAGER CLASS
class DatabaseManager {
    private Map<String, User> users;
    private Map<String, String> passwords;
    private List<Question> questions;
    private List<QuizResult> results;
    
    public DatabaseManager() {
        users = new HashMap<>();
        passwords = new HashMap<>();
        questions = new ArrayList<>();
        results = new ArrayList<>();
        initializeSampleQuestions();
    }
    
    private void initializeSampleQuestions() {
        // Math Questions
        questions.add(new Question("What is 15 + 28?", 
            new String[]{"43", "42", "44", "41"}, 0, "Math", "Easy", 
            "Add the digits separately"));
        questions.add(new Question("What is 12 × 8?", 
            new String[]{"96", "84", "102", "108"}, 0, "Math", "Medium", 
            "Think of 12 × 10 minus 12 × 2"));
        questions.add(new Question("What is the square root of 144?", 
            new String[]{"12", "11", "13", "14"}, 0, "Math", "Easy", 
            "What number multiplied by itself equals 144?"));
        questions.add(new Question("Solve: 3x + 5 = 20", 
            new String[]{"5", "4", "6", "7"}, 0, "Math", "Medium", 
            "Subtract 5 from both sides first"));
        
        // Geography Questions
        questions.add(new Question("What is the capital of France?", 
            new String[]{"Paris", "London", "Berlin", "Madrid"}, 0, "Geography", "Easy", 
            "City of Lights"));
        questions.add(new Question("Which is the largest ocean?", 
            new String[]{"Pacific", "Atlantic", "Indian", "Arctic"}, 0, "Geography", "Easy", 
            "Think about Asia"));
        questions.add(new Question("What is the longest river in the world?", 
            new String[]{"Nile", "Amazon", "Yangtze", "Mississippi"}, 0, "Geography", "Medium", 
            "It flows through Egypt"));
        questions.add(new Question("Which country has the most population?", 
            new String[]{"China", "India", "USA", "Indonesia"}, 0, "Geography", "Easy", 
            "It's in Asia"));
        
        // Science Questions
        questions.add(new Question("What is the chemical symbol for water?", 
            new String[]{"H2O", "CO2", "O2", "H2"}, 0, "Science", "Easy", 
            "Two hydrogen, one oxygen"));
        questions.add(new Question("How many planets are in our solar system?", 
            new String[]{"8", "7", "9", "10"}, 0, "Science", "Easy", 
            "Pluto is not a planet anymore"));
        questions.add(new Question("What is the speed of light?", 
            new String[]{"300,000 km/s", "150,000 km/s", "450,000 km/s", "200,000 km/s"}, 
            0, "Science", "Medium", "Approximately 3 × 10^8 m/s"));
        questions.add(new Question("What gas do plants absorb from the atmosphere?", 
            new String[]{"Carbon Dioxide", "Oxygen", "Nitrogen", "Hydrogen"}, 0, "Science", "Easy", 
            "Used in photosynthesis"));
        
        // English Questions
        questions.add(new Question("Which is a noun?", 
            new String[]{"Cat", "Run", "Quickly", "Beautiful"}, 0, "English", "Easy", 
            "A person, place, or thing"));
        questions.add(new Question("What is the past tense of 'go'?", 
            new String[]{"Went", "Goed", "Gone", "Going"}, 0, "English", "Easy", 
            "Irregular verb"));
        questions.add(new Question("Identify the adjective: 'The quick brown fox jumps'", 
            new String[]{"Quick", "Fox", "Jumps", "The"}, 0, "English", "Medium", 
            "Describes the fox"));
        questions.add(new Question("What is a synonym for 'happy'?", 
            new String[]{"Joyful", "Sad", "Angry", "Tired"}, 0, "English", "Easy", 
            "Same meaning"));
        
        // Computer Science Questions
        questions.add(new Question("What does HTML stand for?", 
            new String[]{"HyperText Markup Language", "High Tech Modern Language", 
                        "Home Tool Markup Language", "Hyperlinks Text Markup Language"}, 
            0, "Computer", "Easy", "Used for web pages"));
        questions.add(new Question("Which language is known for 'Write Once, Run Anywhere'?", 
            new String[]{"Java", "Python", "C++", "JavaScript"}, 0, "Computer", "Medium", 
            "Platform independence"));
        questions.add(new Question("What does CPU stand for?", 
            new String[]{"Central Processing Unit", "Computer Personal Unit", 
                        "Central Program Utility", "Computer Processing Utility"}, 
            0, "Computer", "Easy", "The brain of the computer"));
        questions.add(new Question("What is a variable in programming?", 
            new String[]{"A container for storing data", "A type of loop", 
                        "A function", "An error"}, 
            0, "Computer", "Easy", "Stores values"));
        
        // Daily Challenge Questions
        questions.add(new Question("Which planet is known as the Red Planet?", 
            new String[]{"Mars", "Venus", "Jupiter", "Saturn"}, 0, "Daily", "Easy", 
            "Named after the Roman god of war"));
        questions.add(new Question("Who painted the Mona Lisa?", 
            new String[]{"Leonardo da Vinci", "Pablo Picasso", "Vincent van Gogh", "Michelangelo"}, 
            0, "Daily", "Medium", "Italian Renaissance artist"));
    }
    
    public boolean registerUser(User user, String password) {
        if (users.containsKey(user.getUsername())) {
            return false;
        }
        users.put(user.getUsername(), user);
        passwords.put(user.getUsername(), password);
        return true;
    }
    
    public User loginUser(String username, String password) {
        if (passwords.containsKey(username) && passwords.get(username).equals(password)) {
            return users.get(username);
        }
        return null;
    }
    
    public List<Question> getQuestions(String subject, String difficulty) {
        List<Question> filtered = new ArrayList<>();
        for (Question q : questions) {
            if (q.getSubject().equals(subject)) {
                filtered.add(q);
            }
        }
        Collections.shuffle(filtered);
        return filtered.subList(0, Math.min(10, filtered.size()));
    }
    
    public void addQuestion(Question q) {
        questions.add(q);
    }
    
    public void updateQuestion(Question q) {
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getId() == q.getId()) {
                questions.set(i, q);
                break;
            }
        }
    }
    
    public void deleteQuestion(int id) {
        questions.removeIf(q -> q.getId() == id);
    }
    
    public List<Question> getAllQuestions(String subjectFilter) {
        if (subjectFilter.equals("All")) {
            return new ArrayList<>(questions);
        }
        List<Question> filtered = new ArrayList<>();
        for (Question q : questions) {
            if (q.getSubject().equals(subjectFilter)) {
                filtered.add(q);
            }
        }
        return filtered;
    }
    
    public void saveQuizResult(User user, QuizSession session) {
        user.incrementQuizzes();
        results.add(new QuizResult(user.getUsername(), session.getScore(), 
                                   session.getTotalQuestions(), session.getAccuracy()));
    }
    
    public List<User> getTopUsers(int limit) {
        List<User> userList = new ArrayList<>(users.values());
        userList.sort((u1, u2) -> Integer.compare(u2.getTotalScore(), u1.getTotalScore()));
        return userList.subList(0, Math.min(limit, userList.size()));
    }
    
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }
    
    public List<QuizResult> getUserResults(String username) {
        List<QuizResult> userResults = new ArrayList<>();
        for (QuizResult r : results) {
            if (r.username.equals(username)) {
                userResults.add(r);
            }
        }
        return userResults;
    }
    
    public int getTotalUsers() {
        return users.size();
    }
    
    public int getTotalQuestions() {
        return questions.size();
    }
    
    public int getTotalQuizzesTaken() {
        return results.size();
    }
    
    public List<UserStats> getUserStatistics() {
        List<UserStats> stats = new ArrayList<>();
        for (User user : users.values()) {
            int quizzesTaken = 0;
            double totalAccuracy = 0;
            
            for (QuizResult result : results) {
                if (result.username.equals(user.getUsername())) {
                    quizzesTaken++;
                    totalAccuracy += result.accuracy;
                }
            }
            
            double avgAccuracy = quizzesTaken > 0 ? totalAccuracy / quizzesTaken : 0;
            stats.add(new UserStats(user.getUsername(), quizzesTaken, 
                                   user.getTotalScore(), avgAccuracy, user.getLevel()));
        }
        
        return stats;
    }
}