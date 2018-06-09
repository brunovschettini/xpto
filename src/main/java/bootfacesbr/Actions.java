/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootfacesbr;

import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

@Named
public class Actions {
    
    public void action(ActionEvent event) {
    UIComponent component = event.getComponent();
    String value = (String) component.getAttributes().get("value");}
    
}
