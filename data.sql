USE [master]
GO
/****** Object:  Database [LongSan]    Script Date: 07/05/2018 14:06:33 ******/
CREATE DATABASE [LongSan] ON  PRIMARY 
( NAME = N'LongSan', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\LongSan.mdf' , SIZE = 2048KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'LongSan_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\LongSan_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [LongSan] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [LongSan].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [LongSan] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [LongSan] SET ANSI_NULLS OFF
GO
ALTER DATABASE [LongSan] SET ANSI_PADDING OFF
GO
ALTER DATABASE [LongSan] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [LongSan] SET ARITHABORT OFF
GO
ALTER DATABASE [LongSan] SET AUTO_CLOSE OFF
GO
ALTER DATABASE [LongSan] SET AUTO_CREATE_STATISTICS ON
GO
ALTER DATABASE [LongSan] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [LongSan] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [LongSan] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [LongSan] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [LongSan] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [LongSan] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [LongSan] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [LongSan] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [LongSan] SET  DISABLE_BROKER
GO
ALTER DATABASE [LongSan] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [LongSan] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [LongSan] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [LongSan] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [LongSan] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [LongSan] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [LongSan] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [LongSan] SET  READ_WRITE
GO
ALTER DATABASE [LongSan] SET RECOVERY SIMPLE
GO
ALTER DATABASE [LongSan] SET  MULTI_USER
GO
ALTER DATABASE [LongSan] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [LongSan] SET DB_CHAINING OFF
GO
USE [LongSan]
GO
/****** Object:  Table [dbo].[subject]    Script Date: 07/05/2018 14:06:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[subject](
	[subjectID] [varchar](8) NOT NULL,
	[subjectName] [varchar](50) NOT NULL,
	[NoOfSlot] [int] NOT NULL,
	[prerequisite] [varchar](250) NOT NULL,
	[credits] [int] NOT NULL,
 CONSTRAINT [PK_subject] PRIMARY KEY CLUSTERED 
(
	[subjectID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[subject] ([subjectID], [subjectName], [NoOfSlot], [prerequisite], [credits]) VALUES (N'CSI101', N'Connecting to Computer Science', 30, N'Passed Semester Summit2', 3)
INSERT [dbo].[subject] ([subjectID], [subjectName], [NoOfSlot], [prerequisite], [credits]) VALUES (N'JPD111', N'Japanese 1', 30, N'Passed Semester 2', 3)
INSERT [dbo].[subject] ([subjectID], [subjectName], [NoOfSlot], [prerequisite], [credits]) VALUES (N'JPD121', N'Japanese Elementary 2', 30, N'Passed JPD111', 3)
INSERT [dbo].[subject] ([subjectID], [subjectName], [NoOfSlot], [prerequisite], [credits]) VALUES (N'PRF192', N'Programming Fundamentals', 30, N'Passed Semester Summit2', 3)
INSERT [dbo].[subject] ([subjectID], [subjectName], [NoOfSlot], [prerequisite], [credits]) VALUES (N'PRJ311', N'Desktop Java Applications', 30, N'Passed PRO192', 3)
INSERT [dbo].[subject] ([subjectID], [subjectName], [NoOfSlot], [prerequisite], [credits]) VALUES (N'PRJ321', N'Application Web', 30, N'Passed PRJ311', 3)
INSERT [dbo].[subject] ([subjectID], [subjectName], [NoOfSlot], [prerequisite], [credits]) VALUES (N'PRO001', N'Programming with Alice', 30, N'Passed Semester Summit1', 3)
INSERT [dbo].[subject] ([subjectID], [subjectName], [NoOfSlot], [prerequisite], [credits]) VALUES (N'PRO192', N'Object-Oriented Programming', 30, N'Passed PRF192', 3)
/****** Object:  Table [dbo].[student]    Script Date: 07/05/2018 14:06:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[student](
	[studentID] [varchar](8) NOT NULL,
	[firstName] [varchar](15) NOT NULL,
	[lastName] [varchar](15) NOT NULL,
	[middleName] [varchar](15) NOT NULL,
	[address] [varchar](250) NULL,
	[phone] [varchar](11) NULL,
	[sex] [bit] NOT NULL,
 CONSTRAINT [PK_student] PRIMARY KEY CLUSTERED 
(
	[studentID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[student] ([studentID], [firstName], [lastName], [middleName], [address], [phone], [sex]) VALUES (N'SE62792', N'Long', N'Dong', N'Bao', N'Tan Binh', N'0906608005', 1)
INSERT [dbo].[student] ([studentID], [firstName], [lastName], [middleName], [address], [phone], [sex]) VALUES (N'SE62793', N'Thang', N'Nguyen', N'Le Duy', N'Quan 1', N'0123456789', 1)
INSERT [dbo].[student] ([studentID], [firstName], [lastName], [middleName], [address], [phone], [sex]) VALUES (N'SE62794', N'Le', N'Dang', N'Huu', N'Hoc Moon', N'1234567890', 1)
INSERT [dbo].[student] ([studentID], [firstName], [lastName], [middleName], [address], [phone], [sex]) VALUES (N'SE62795', N'Phu', N'Nguyen', N'Ngoc', N'Quan 2', N'9876543210', 1)
/****** Object:  Table [dbo].[marks]    Script Date: 07/05/2018 14:06:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[marks](
	[id] [int] NOT NULL,
	[classID] [varchar](8) NOT NULL,
	[studentID] [varchar](8) NOT NULL,
	[subjectID] [varchar](8) NOT NULL,
	[blockSemester] [varchar](12) NOT NULL,
	[subjectAvg] [float] NOT NULL,
	[status] [varchar](12) NOT NULL,
 CONSTRAINT [PK_marks] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[marks] ([id], [classID], [studentID], [subjectID], [blockSemester], [subjectAvg], [status]) VALUES (1, N'SE1270', N'SE62792', N'CSI101', N'Summer2017_1', 6, N'Passed')
INSERT [dbo].[marks] ([id], [classID], [studentID], [subjectID], [blockSemester], [subjectAvg], [status]) VALUES (2, N'SE1270', N'SE62792', N'PRF192', N'Summer2017_1', 6, N'Passed')
INSERT [dbo].[marks] ([id], [classID], [studentID], [subjectID], [blockSemester], [subjectAvg], [status]) VALUES (3, N'SE1270', N'SE62792', N'PRO001', N'Summer2017_1', 5, N'Passed')
INSERT [dbo].[marks] ([id], [classID], [studentID], [subjectID], [blockSemester], [subjectAvg], [status]) VALUES (4, N'SE1270', N'SE62792', N'JPD111', N'Fall2017_1', 8.2, N'Passed')
INSERT [dbo].[marks] ([id], [classID], [studentID], [subjectID], [blockSemester], [subjectAvg], [status]) VALUES (5, N'SE1270', N'SE62792', N'PRO192', N'Fall2017_1', 6, N'Passed')
INSERT [dbo].[marks] ([id], [classID], [studentID], [subjectID], [blockSemester], [subjectAvg], [status]) VALUES (6, N'SE1270', N'SE62792', N'PRJ311', N'Fall2017_1', 5.2, N'Paseed')
INSERT [dbo].[marks] ([id], [classID], [studentID], [subjectID], [blockSemester], [subjectAvg], [status]) VALUES (7, N'SE1270', N'SE62792', N'JPD121', N'Summer2018_1', 0, N'Not Started')
INSERT [dbo].[marks] ([id], [classID], [studentID], [subjectID], [blockSemester], [subjectAvg], [status]) VALUES (8, N'SE1269', N'SE62792', N'PRJ321', N'Summer2018_1', 0, N'Not Started')
INSERT [dbo].[marks] ([id], [classID], [studentID], [subjectID], [blockSemester], [subjectAvg], [status]) VALUES (9, N'SE1270', N'SE62793', N'CSI101', N'Summer2017_1', 7.5, N'Passed')
INSERT [dbo].[marks] ([id], [classID], [studentID], [subjectID], [blockSemester], [subjectAvg], [status]) VALUES (10, N'SE1270', N'SE62793', N'PRF192', N'Summer2017_1', 4, N'Not Passed')
INSERT [dbo].[marks] ([id], [classID], [studentID], [subjectID], [blockSemester], [subjectAvg], [status]) VALUES (11, N'SE1270', N'SE62793', N'PRO001', N'Summer2017_1', 5, N'Passed')
INSERT [dbo].[marks] ([id], [classID], [studentID], [subjectID], [blockSemester], [subjectAvg], [status]) VALUES (12, N'SE1270', N'SE62793', N'JPD111', N'Fall2017_1', 6, N'Passed')
INSERT [dbo].[marks] ([id], [classID], [studentID], [subjectID], [blockSemester], [subjectAvg], [status]) VALUES (13, N'SE1270', N'SE62793', N'PRO192', N'Fall2017_1', 7, N'Not Passed')
INSERT [dbo].[marks] ([id], [classID], [studentID], [subjectID], [blockSemester], [subjectAvg], [status]) VALUES (14, N'SE1278', N'SE62793', N'PRJ311', N'Spring2018_1', 3.5, N'Not Passed')
INSERT [dbo].[marks] ([id], [classID], [studentID], [subjectID], [blockSemester], [subjectAvg], [status]) VALUES (15, N'SE1270', N'SE62793', N'JPD121', N'Summer2018_1', 0, N'Not Started')
INSERT [dbo].[marks] ([id], [classID], [studentID], [subjectID], [blockSemester], [subjectAvg], [status]) VALUES (16, N'SE1268', N'SE62793', N'PRJ321', N'Summer2018_1', 0, N'Not Started')
INSERT [dbo].[marks] ([id], [classID], [studentID], [subjectID], [blockSemester], [subjectAvg], [status]) VALUES (17, N'SE1234', N'SE62793', N'PRF192', N'Fall2017_2', 6, N'Passed')
INSERT [dbo].[marks] ([id], [classID], [studentID], [subjectID], [blockSemester], [subjectAvg], [status]) VALUES (18, N'SE1235', N'SE62793', N'PRJ311', N'Spring2018_2', 6.5, N'Passed')
INSERT [dbo].[marks] ([id], [classID], [studentID], [subjectID], [blockSemester], [subjectAvg], [status]) VALUES (19, N'SE1236', N'SE62793', N'PRF192', N'Spring2018_3', 7.5, N'Improved')
/****** Object:  Table [dbo].[feedback]    Script Date: 07/05/2018 14:06:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[feedback](
	[id] [int] NOT NULL,
	[fbDate] [datetime] NULL,
	[contents] [nvarchar](250) NULL,
	[studentID] [varchar](8) NULL,
	[status] [bit] NULL,
 CONSTRAINT [PK_feedback] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[feedback] ([id], [fbDate], [contents], [studentID], [status]) VALUES (1, CAST(0x0000A91400E0E421 AS DateTime), N'subjectID: CSI101_Block: 1_contents: diem sai', N'SE62793', 0)
INSERT [dbo].[feedback] ([id], [fbDate], [contents], [studentID], [status]) VALUES (2, CAST(0x0000A91400E0E423 AS DateTime), N'subjectID: PRO001_Block: 1_contents: nham trang thai', N'SE62793', 0)
INSERT [dbo].[feedback] ([id], [fbDate], [contents], [studentID], [status]) VALUES (3, CAST(0x0000A91400E0E428 AS DateTime), N'subjectID: PRF192_Block: 1_contents: diem sai', N'SE62793', 0)
/****** Object:  Table [dbo].[account]    Script Date: 07/05/2018 14:06:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[account](
	[username] [varchar](8) NOT NULL,
	[password] [varchar](25) NOT NULL,
	[status] [varchar](10) NOT NULL,
 CONSTRAINT [PK_account] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[account] ([username], [password], [status]) VALUES (N'SE62792', N'123', N'studying')
INSERT [dbo].[account] ([username], [password], [status]) VALUES (N'SE62793', N'123', N'studying')
INSERT [dbo].[account] ([username], [password], [status]) VALUES (N'SE62794', N'123', N'break')
INSERT [dbo].[account] ([username], [password], [status]) VALUES (N'SE62795', N'123', N'dropout')
/****** Object:  ForeignKey [FK_marks_student]    Script Date: 07/05/2018 14:06:34 ******/
ALTER TABLE [dbo].[marks]  WITH CHECK ADD  CONSTRAINT [FK_marks_student] FOREIGN KEY([studentID])
REFERENCES [dbo].[student] ([studentID])
GO
ALTER TABLE [dbo].[marks] CHECK CONSTRAINT [FK_marks_student]
GO
/****** Object:  ForeignKey [FK_marks_subject]    Script Date: 07/05/2018 14:06:34 ******/
ALTER TABLE [dbo].[marks]  WITH CHECK ADD  CONSTRAINT [FK_marks_subject] FOREIGN KEY([subjectID])
REFERENCES [dbo].[subject] ([subjectID])
GO
ALTER TABLE [dbo].[marks] CHECK CONSTRAINT [FK_marks_subject]
GO
/****** Object:  ForeignKey [FK_feedback_student]    Script Date: 07/05/2018 14:06:34 ******/
ALTER TABLE [dbo].[feedback]  WITH CHECK ADD  CONSTRAINT [FK_feedback_student] FOREIGN KEY([studentID])
REFERENCES [dbo].[student] ([studentID])
GO
ALTER TABLE [dbo].[feedback] CHECK CONSTRAINT [FK_feedback_student]
GO
/****** Object:  ForeignKey [FK_account_student]    Script Date: 07/05/2018 14:06:34 ******/
ALTER TABLE [dbo].[account]  WITH CHECK ADD  CONSTRAINT [FK_account_student] FOREIGN KEY([username])
REFERENCES [dbo].[student] ([studentID])
GO
ALTER TABLE [dbo].[account] CHECK CONSTRAINT [FK_account_student]
GO
