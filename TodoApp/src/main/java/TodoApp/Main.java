/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TodoApp;

import java.sql.Connection;
import util.ConnetcionFactory;

/**
 *
 * @author robson
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Connection c = ConnetcionFactory.getConnection();// abre conexao
        
        ConnetcionFactory.closeConnection(c); // fecha conexao caso nao precisar        
    }
    
}
