package com.GitLabReviewer.GR.DataBase.UserDB;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
