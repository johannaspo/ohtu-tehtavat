
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {
 
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }  
    
    @Test
    public void playerSearchFullName() {
        Player player = stats.search("Semenko");
        assertEquals("Semenko", player.getName());
    }
    
    @Test
    public void playerSearchPartialName() {
        Player player = stats.search("Sem");
        assertEquals("Semenko", player.getName());
    }
    
    @Test
    public void playerSearchNameNotFound() {
        Player player = stats.search("Olematon");
        assertNull(player);
    }
    
    @Test
    public void teamSearch() {
        List<Player> edm = stats.team("EDM");
        assertEquals(3, edm.size());
    }
    
    @Test
    public void teamSearchNotFound() {
        List<Player> not = stats.team("NOT");
        assertEquals(0, not.size());
    }
    
    @Test
    public void topScorersFound() {
        List<Player> topScorers = stats.topScorers(5);
        assertEquals(5, topScorers.size());
    }
    
    @Test
    public void topScorersTooMany() {
        List<Player> top = stats.topScorers(6);
        assertEquals(5, top.size());
    }
    
}
