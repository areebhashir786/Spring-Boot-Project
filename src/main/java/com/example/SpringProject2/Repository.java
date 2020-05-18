
package com.example.SpringProject2;

import com.example.SpringProject2.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author areeb
 */

public interface Repository extends JpaRepository<User,String>{
    
}
