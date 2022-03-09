package datamodel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @since J2SE-1.8
 CREATE TABLE employee (
  id INT NOT NULL AUTO_INCREMENT,    
  name VARCHAR(30) NOT NULL,   
  age INT NOT NULL,    
  PRIMARY KEY (id));
 */
@Entity
@Table(name = "teams")
public class Team {

   @Id  // primary key
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id") // specify the column name. Without it, it will use method name
   private Integer id;

   @Column(name = "name")
   private String name;

   @Column(name = "owner")
   private String owner;
   
   @Column(name = "wins")
   private Integer wins;

   public Team() {
   }

   public Team(Integer id, String name, String owner, Integer wins) {
      this.id = id;
      this.name = name;
      this.owner = owner;
      this.wins = wins;
   }

   public Team(String name, String owner, Integer wins) {
      this.name = name;
      this.owner = owner;
      this.wins = wins;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getOwner() {
      return owner;
   }

   public void setOwner(String owner) {
      this.owner = owner;
   }
   
   public Integer getWins() {
	  return wins;
   }

   public void setWins(Integer wins) {
	  this.wins = wins;
   }

   @Override
   public String toString() {
      return "Yakipoo Team Record: " + this.id + ", " + this.name + ", " + this.owner + ", " + this.wins;
   }
}