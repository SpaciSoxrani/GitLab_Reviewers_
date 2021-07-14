package com.GitLabReviewer.GR.DataBase.WebHookDB;

import com.GitLabReviewer.GR.WebhookTypes.MergeRequestWebhook.MergeRequestWebhook;
import com.GitLabReviewer.GR.WebhookTypes.PushWebhook.PushWebhook;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WebHookRepository extends JpaRepository<PushWebhook, Long> {
}