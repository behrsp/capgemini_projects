/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Task;
import util.ConnetcionFactory;

/**
 *
 * @author robson
 */
public class TaskController {
    
    public void save(Task task){
            
        String sql = "INSERT INTO tasks (idProject"
                + "name,"
                + "description,"
                + "notes,"
                + "deadline,"
                + "createdAt,"
                + "updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try{
            connection = ConnetcionFactory.getConnection;
            statement = connection.PreparedStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.execute();
            
        }catch(Exception e){
            throw new RuntimeException("Erro ao salvar tarefa " 
                                        + e.getMessage(),e);
        }finally{
            ConnetcionFactory.closeConnection(connection, statement);
                
        }
    }
    
    public void update(Task task){
       String sql = "UPDATE tasks SET"
               + "idProject = ?,"
               + "name = ?,"
               + "description = ?,"
               + "notes = ?, "
               + "completed = ? ,"
               + "deadline = ?,"
               + "createdAt = ? ,"
               + "updatedAt = ?,"
               + " WHERE id = ? ";

       Connection connection = null;
       PreparedStatement statement = null;
       
       try{
           connection = ConnetcionFactory.getConnection();
           statement = connection.prepareStatement(sql);
           statement.setInt(1,task.getIdProject());
           statement.setString(2,task.getName());
           statement.setString(3,task.getDescription());
           statement.setString(4,task.getNotes());
           statement.setBoolean(5,task.isIsCompleted());
           statement.setDate(6,new Date(task.getDeadline().getTime()));
           statement.setDate(6,new Date(task.getCreatedAt().getTime()));
           statement.setDate(6,new Date(task.getUpdatedAt().getTime()));
           statement.execute();
       }catch(Exception e){
           throw new RuntimeException("Erro ao Atualizar tarefa.", e);
       }               
    }
    
    public void removeById(int taskId) throws SQLException{
        String sql = "DELETE FROM tasks WHERE id = ? "; 
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try{
            connection = ConnetcionFactory.getConnection();
            statement = connection.PrepareStatement(sql);
            statement.setInt(1, taskId);
            statement.execute();
        }catch (Exception e){
            throw new RuntimeException("Erro ao Deletar tarefa.", e);
        }finally{
            ConnetcionFactory.closeConnection(connection, statement);
        }
        
        
    }
    
    public List<Task> getAll(int idProject){
        
        String sql = "SELECT * FROM tasks WHERE idProject = ?";
        
        Connection connection = null;
        PrepareStatement statement = null;
        ResultSet resultSet = null;
        
        List<Task> tasks = new ArrayList<Task>();
        
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idProject);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                
                Task task = new Task();
                task.setId(resultSet.getInt("Id"));
                task.setIdProject(resultSet.getInt("IdProject"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setNotes(resultSet.getString("notes"));
                task.setIsCompleted(resultSet.getBoolean("completed"));
                task.setDeadline(resultSet.getDate("deadline"));
                task.setCreatedAt(resultSet.getDate("createdAt"));
                task.setUpdatedAt(resultSet.getDate("updatedAt"));
                
                task.add(task);
                
            }
        }catch(Exception e){
            
        }
        
        
        
        
        
        
        return null;
    }
}
