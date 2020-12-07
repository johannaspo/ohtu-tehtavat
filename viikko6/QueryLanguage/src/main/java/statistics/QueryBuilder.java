package statistics;

import statistics.matcher.All;
import statistics.matcher.And;
import statistics.matcher.HasAtLeast;
import statistics.matcher.HasFewerThan;
import statistics.matcher.Matcher;
import statistics.matcher.Or;
import statistics.matcher.PlaysIn;

public class QueryBuilder {
    Matcher matcher;
    
    public QueryBuilder() {
        matcher = new All();
    }
    
    public Matcher build() {
        return matcher;
    }
    
    public QueryBuilder playsIn(String team) {
        this.matcher = new And(this.matcher, new PlaysIn(team));
        return this;
    }
    
    public QueryBuilder hasAtLeast(int number, String type) {
        this.matcher = new And(this.matcher, new HasAtLeast(number, type));
        return this;
    }
    
    public QueryBuilder hasFewerThan(int number, String type) {
        this.matcher = new And(this.matcher, new HasFewerThan(number, type));
        return this;
    }
    
    public QueryBuilder oneOf(Matcher... matchers){
        this.matcher = new And(this.matcher, new Or(matchers));
        return this;
    }
}
