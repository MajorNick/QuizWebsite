<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <meta charset="UTF-8">
    <head>
        <title>User</title>
    </head>
    <style>
        .navbar {
            background-color: #007bff;
            align-items: center;
            display: flex;
        }

        .navbarItem {
            padding: 5px;
        }

        input.navbarItem {
            border: 2px solid #ccc;
            resize: none;
        }

        button.navbarItem {
            padding: 20px;
            background-color: transparent;
            border: none;
            color: #ffffff;
            cursor: pointer;
            font-size: 20px;
            font-weight: bold;
        }

        .block-container {
            display: flex;
            /* align-items: center; */
            justify-content: center;
            margin-top: 10px;
            padding: 20px;
            border: 2px solid #007bff;
            border-radius: 12px;
        }

        .block-contents {
            display: flex;
            flex-direction: column;
            width: 100%;
        }

        .block-title {
            font-size: 40px;
        }

        .block-items {
            font-size: 20px;
        }

        .left-side {
            display: flex;
            flex-direction: column;
            align-items: center;
            padding-right: 20px;
        }

        .profile-image {
            width: 200px;
            height: 200px;
            object-fit: cover;
            border-radius: 5%;
        }

        .username {
            font-size: 24px;
            margin-top: 10px;
        }

        .right-side {
            flex: 1;
            display: flex;
            flex-direction: column;
            align-items: flex-start;
        }

        .action-button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 20px;
            margin-top: 10px;
            cursor: pointer;
            border-radius: 5px;
        }
    </style>
    <body>
        <div class="navbar">
            <button class="navbarItem">Home</button>
            <div>
            <input  class="navbarItem" rows="1" cols="30" placeholder="Look up user"/>
            </div>
        </div>
        <div class="block-container">
            <div class="left-side">
                <img class="profile-image" src="https://via.placeholder.com/200x200" alt="Profile Image">
                <div class="username">John Doe</div>
            </div>
            <div class="right-side">
                <button class="action-button">Add friend</button>
                <button class="action-button">Challenge</button>
                <button class="action-button">Note</button>
            </div>
        </div>
        <div class="block-container">
            <div class="block-contents">
                <div class="block-title">History</div>
                <div class="block-items">
                    <ol>
                        <li>List here</li>
                        <li>List here</li>
                        <li>List here</li>
                        <li>List here</li>
                    </ol>
                </div>
            </div>
        </div>
        <div class="block-container">
            <div class="block-contents">
                <div class="block-title">Achievements</div>
                <div class="block-items">
                    <ol>
                        <li>List here</li>
                        <li>List here</li>
                        <li>List here</li>
                        <li>List here</li>
                    </ol>
                </div class="block-items">
            </div>
        </div>
    </body>
</html>
