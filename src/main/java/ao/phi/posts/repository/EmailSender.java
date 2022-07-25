package ao.phi.posts.repository;

public interface EmailSender {
    void send (String to, String email);
}
