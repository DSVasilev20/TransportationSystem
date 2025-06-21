CREATE DATABASE PackageManagementSystemDB;
USE PackageManagementSystemDB;

CREATE TABLE Users (
    Id INT PRIMARY KEY IDENTITY(1, 1),
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Email VARCHAR(50) NOT NULL,
    PhoneNumber VARCHAR(50) NOT NULL
);

CREATE TABLE Packages (
    Id INT PRIMARY KEY IDENTITY(1, 1),
    SenderUserID INT NOT NULL,
    ReceiveUserID INT NOT NULL,
    [Description] VARCHAR (50) NOT NULL,
    [Status] VARCHAR(50) NOT NULL,
    CurrentLocation VARCHAR(50) NOT NULL,
    DeliveryDate DATETIME NOT NULL,
    CONSTRAINT FK_Packages_Users_SenderUserID FOREIGN KEY (SenderUserID) REFERENCES Users (Id),
    CONSTRAINT FK_Packages_Users_ReceiveUserID FOREIGN KEY (ReceiveUserID) REFERENCES Users (Id),
);

CREATE TABLE Locations (
    Id INT PRIMARY KEY IDENTITY(1, 1),
    PackageID INT NOT NULL,
    [Timestamp] DATETIME NOT NULL,
    Latitude DECIMAL(18, 0) NOT NULL,
    Longitude DECIMAL(18, 0) NOT NULL,
    CONSTRAINT FK_Locations_Packages FOREIGN KEY (PackageID) REFERENCES Packages (Id)
);

CREATE TABLE TransportationVehicles (
    Id INT PRIMARY KEY IDENTITY(1, 1),
    VehicleType VARCHAR(50) NOT NULL,
    CurrentLocation VARCHAR(50) NOT NULL,
    Capacity INT NOT NULL CREATE TABLE Employees(
    Id INT PRIMARY KEY IDENTITY (1, 1),
    FirstName VARCHAR (50) NOT NULL,
    LastName VARCHAR (50) NOT NULL,
    Email VARCHAR (50) NOT NULL,
    Password VARCHAR (50) NOT NULL
);
