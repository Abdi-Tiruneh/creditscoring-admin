package com.dxvalley.creditscoring.emailManager;

import com.dxvalley.creditscoring.utils.ApiResponse;
import io.micrometer.common.lang.NonNull;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Implementation of the EmailService interface for sending emails.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public CompletableFuture<ApiResponse> send(@NonNull String recipientEmail, @NonNull String emailBody, @NonNull String emailSubject) {
        try {
            MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(emailBody, true);
            helper.setTo(recipientEmail);
            helper.setSubject(emailSubject);
            this.mailSender.send(mimeMessage);
            return CompletableFuture.completedFuture(new ApiResponse(HttpStatus.OK, "Email sent successfully."));
        } catch (MailException | MessagingException ex) {
            log.error("An error occurred in {}.{} while sending email. Details: {}", new Object[]{this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage()});
            return CompletableFuture.completedFuture(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Cannot send email due to Internal Server Error. Please try again later."));
        }
    }
}
