package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
    
    private Pankki pankki;
    private Viitegeneraattori viite;
    private Varasto varasto;
    private Kauppa k; 
    
    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        k = new Kauppa(varasto, pankki, viite);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeallaAsiakkaallaTilinumerollaJaSummalla() {
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455",5);   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    public void kahdenEriOstoksenJalkeenPankinMetodiaTilisiirtoKutsutaanOikeallaAsiakkaallaTilinumerollaJaSummalla() {
        when(varasto.saldo(2)).thenReturn(10); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kahvi", 6));

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1); 
        k.lisaaKoriin(2);  // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455",11);   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    public void kahdenSamanOstoksenJalkeenPankinMetodiaTilisiirtoKutsutaanOikeallaAsiakkaallaTilinumerollaJaSummalla() {
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1); 
        k.lisaaKoriin(1);  // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455",10);   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    public void kahdenEriOstoksenJalkeenPankinMetodiaTilisiirtoKutsutaanOikeallaAsiakkaallaTilinumerollaJaSummallaKunToistaTuotettaEiOle() {
        when(varasto.saldo(2)).thenReturn(0); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kahvi", 6));

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1); 
        k.lisaaKoriin(2);  // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455",5);   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
}