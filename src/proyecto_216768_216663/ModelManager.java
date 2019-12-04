/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_216768_216663;

/**
 *
 * @author mortisblack
 */
public class ModelManager {
    private int id = 1;

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }
    
    public void increase(){
        this.setId(this.id + 1);
    }
    
    
}
