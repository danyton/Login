@RestController
public class UserController {
	private Database db;
	
	public UserController() throws SQLException {
		db = new Database();
	}
	
	@PostMapping("/register")
	public String register(@RequestParam String username, @RequestParam String password) {
		try {
			User user = db.getUser(username);
			if (user != null) {
				return "User already exists";
			}
			user = new User(username, password);
			db.insertUser(user);
			return "Registration successful";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Registration failed";
		}
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password) {
		try {
			User user = db.getUser(username);
			if (user == null) {
				return "User does not exist";
			}
			if (!user.getPassword().equals(password)) {
				return "Incorrect password";
			}
			return "Login successful";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Login failed";
		}
	}
}
