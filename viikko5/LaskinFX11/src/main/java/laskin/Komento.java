package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public abstract class Komento {
    TextField tuloskentta; 
    TextField syotekentta; 
    Button plus;
    Button miinus;
    Button nollaa;
    Button undo;
    Sovelluslogiikka sovellus;
    
    public Komento(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.plus = plus;
        this.miinus = miinus;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();
    }

    public abstract void suorita();
    
    public abstract void peru();
}