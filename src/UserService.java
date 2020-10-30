public interface UserService {
    void register(String log, String data);

    boolean authorized(String log, String pass);
}
