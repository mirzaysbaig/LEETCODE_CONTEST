package com.crio.qcontest.services;

 import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.crio.qcontest.entities.User;
import com.crio.qcontest.repositories.IUserRepository;
class userComparator implements Comparator<User>{
    private String order;
    userComparator(String order){
        this.order=order.toLowerCase();
    }
    @Override
    public int compare(User u1,User u2){
        if(order.equals("desc"))
          return Integer.compare(u2.getScore(),u1.getScore());
        else 
          return Integer.compare(u1.getScore(), u2.getScore());
    }
}

public class UserService{

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates a new user with the specified name.
     * @param name Name of the user.
     * @return Created User object.
     */
    public User createUser(String name) {
        User user=new User(name);
        User user1=userRepository.save(user);
        return user1;
    }

    /**
     * Retrieves a list of users sorted by their score.
     * @param order Sorting order ("ASC" for ascending, "DESC" for descending).
     * @return List of users sorted by score as per the specified order.
     */
    public List<User> showLeaderBoard(String order) {
        List<User> user=userRepository.findAll();
        userComparator comparator=new userComparator(order);
        Collections.sort(user,comparator);
        return user;  
    } 
}