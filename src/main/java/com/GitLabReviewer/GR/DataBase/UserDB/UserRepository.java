package com.GitLabReviewer.GR.DataBase.UserDB;

import com.GitLabReviewer.GR.DataBase.UserDB.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
