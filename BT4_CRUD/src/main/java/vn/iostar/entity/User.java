package vn.iostar.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
@NamedQuery(name = "User.findAll", query = "SELECT v FROM User v")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	@Column(name = "username")
	private String username;
	@Column(name = "fullname", columnDefinition = "NVARCHAR(500) NULL")
	private String fullname;
	@Column(name = "password", columnDefinition = "NVARCHAR(500) NULL")
	private String password;
	@Column(name = "images", columnDefinition = "NVARCHAR(500) NULL")
	private String images;

	// bi-directional many-to-one association to Category
}
