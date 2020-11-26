package dk.kea.obopgave.repository;

import dk.kea.obopgave.models.Dog;
import dk.kea.obopgave.utilities.DatabaseConnectionManager;
import dk.kea.obopgave.utilities.ICrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DogRepository implements ICrudRepository<Dog> {

    private Connection conn;

    public DogRepository() {
        this.conn = DatabaseConnectionManager.getDb().getConn();
    }

    @Override
    public void create(Dog dog) {
        String sqlStatement = "INSERT INTO dogs (dog_type, dog_name, dog_age) VALUES (?, ? ,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sqlStatement);
            ps.setString(1, dog.getType());
            ps.setString(2, dog.getName());
            ps.setLong(3, dog.getAge());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Dog> readAll() {
        ArrayList<Dog> dogList = new ArrayList<>();
        String sqlStatement = "SELECT * FROM dogs";
        try {
            PreparedStatement ps = conn.prepareStatement(sqlStatement);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                dogList.add(
                        new Dog(
                                rs.getInt("dog_id"),
                                rs.getString("dog_type"),
                                rs.getString("dog_name"),
                                rs.getInt("dog_age")
                        )
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dogList;
    }

    @Override
    public Dog read(long id){
        Dog dog = null;
        String sqlStatement = "SELECT * FROM dogs WHERE dog_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sqlStatement);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                dog = new Dog(
                        rs.getInt("dog_id"),
                        rs.getString("dog_type"),
                        rs.getString("dog_name"),
                        rs.getInt("dog_age")
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dog;
    }

    @Override
    public boolean update(Dog dog) {
        boolean result = false;
        String sql = "UPDATE dogs SET dog_type = ?, dog_name = ?, dog_age = ?" + "WHERE dog_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, dog.getType());
            ps.setString(2, dog.getName());
            ps.setInt(3, dog.getAge());
            ps.setInt(4, dog.getId());
            int row = ps.executeUpdate();
            if(row > 0 ){
                System.out.println("Updated");
                result = true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(long id) {
        boolean result = false;
        String sql = "DELETE FROM dogs WHERE dog_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            int row = ps.executeUpdate();
            if (row > 0) {
                System.out.println("deleted");
                result = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
