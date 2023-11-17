-- Users table to store user information
CREATE TABLE Users (
    UserID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Role ENUM('Administrator', 'Moderator', 'Student', 'Reviewer') NOT NULL,
    GroupName VARCHAR(255),
    Faculty VARCHAR(255),
    StudyProgram VARCHAR(255),
    UNIQUE (Email) -- Ensure unique email addresses
);

-- Constraint to enforce attributes based on user roles
ALTER TABLE Users
ADD CONSTRAINT check_user_attributes
CHECK (
    -- Administrator: does not have Faculty, StudyProgram, GroupName
    (Role = 'Administrator' AND Faculty IS NULL AND StudyProgram IS NULL AND GroupName IS NULL) OR

    -- Moderator: does not have StudyProgram or GroupName
    (Role = 'Moderator' AND StudyProgram IS NULL AND GroupName IS NULL) OR

    -- Reviewer: does not have GroupName
    (Role = 'Reviewer' AND GroupName IS NULL) OR

    -- Student: has all attributes
    (Role = 'Student' AND Faculty IS NOT NULL AND StudyProgram IS NOT NULL AND GroupName IS NOT NULL)
);


-- Sample data for the Users table
INSERT INTO Users (Name, Email, Password, Role, GroupName, Faculty, StudyProgram)
VALUES
    ('John Doe', 'john.doe@example.com', 'password123', 'Administrator', NULL, NULL, NULL),
    ('Jane Moderator', 'jane.moderator@example.com', 'password456', 'Moderator', NULL, 'Computer Science', NULL),
    ('Alice Reviewer', 'alice.reviewer@example.com', 'password789', 'Reviewer', NULL, NULL, NULL),
    ('Bob Student', 'bob.student@example.com', 'passwordabc', 'Student', 'GroupA', 'Mathematics', 'Physics'),
    ('Charlie Student', 'charlie.student@example.com', 'passworddef', 'Student', 'GroupB', 'Chemistry', 'Biology');

-- Folders table to store folders for each student
CREATE TABLE Folders (
    FolderID INT PRIMARY KEY AUTO_INCREMENT,
    FolderName VARCHAR(255) NOT NULL,
    StudentID INT,
    FOREIGN KEY (StudentID) REFERENCES Users(UserID) ON DELETE CASCADE
);

-- Documents table to store information about uploaded documents
CREATE TABLE Documents (
    DocumentID INT PRIMARY KEY AUTO_INCREMENT,
    FileName VARCHAR(255) NOT NULL,
    FilePath VARCHAR(255) NOT NULL,
    UploadDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UploaderID INT,
    FolderID INT,
    FOREIGN KEY (UploaderID) REFERENCES Users(UserID),
    FOREIGN KEY (FolderID) REFERENCES Folders(FolderID)
);

-- Assignments table to track assignments of reviewers to students
CREATE TABLE Assignments (
    AssignmentID INT PRIMARY KEY AUTO_INCREMENT,
    ReviewerID INT,
    StudentID INT,
    FOREIGN KEY (ReviewerID) REFERENCES Users(UserID),
    FOREIGN KEY (StudentID) REFERENCES Users(UserID)
);


-- Sample data for the Folders table
INSERT INTO Folders (FolderName, StudentID)
VALUES
    ('Folder_A', 4),  -- Belongs to Bob Student
    ('Folder_B', 5);  -- Belongs to Charlie Student

-- Sample data for the Documents table
INSERT INTO Documents (FileName, FilePath, UploaderID, FolderID)
VALUES
    ('Document1.pdf', '/documents/doc1.pdf', 2, 1),  -- Uploaded by Jane Moderator to Folder_A
    ('Document2.doc', '/documents/doc2.doc', 3, 2);  -- Uploaded by Alice Reviewer to Folder_B

-- Sample data for the Assignments table
INSERT INTO Assignments (ReviewerID, StudentID)
VALUES
    (3, 4),  -- Alice Reviewer assigned to review Bob Student
    (3, 5);  -- Alice Reviewer assigned to review Charlie Student






