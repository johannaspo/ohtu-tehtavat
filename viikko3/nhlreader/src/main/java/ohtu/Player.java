
package ohtu;

public class Player {
    private int goals;
    private int assists;
    private String name;
    private int penalties;
    private String team;
    private String nationality;

    public void setGoals(int goals) {
        this.goals = goals;
    }
    
    public void setAssists(int assists) {
        this.assists = assists;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }
    
    public void setTeam(String team) {
        this.team = team;
    }
    
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    public int getGoals() {
        return goals;
    }
    
    public int getAssists() {
        return assists;
    }

    public String getName() {
        return name;
    }
    
    public int getPenalties() {
        return penalties;
    }
    
    public String getTeam() {
        return team;
    }
    
    public String getNationality() {
        return nationality;
    }

    @Override
    public String toString() {
        return name + " team " + team + " goals " + goals + " assists " + assists;
    }
    
    
      
}
