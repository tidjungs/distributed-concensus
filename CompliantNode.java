import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;
import static java.util.stream.Collectors.toSet;

/* CompliantNode refers to a node that follows the rules (not malicious)*/
public class CompliantNode implements Node {
    private double p_graph;
    private double p_malicious;
    private double p_tXDistribution;
    private int numRounds;

    private boolean[] followees;
    private boolean[] blacklists;
    private Set<Transaction> pendingTransactions;
    

    public CompliantNode(double p_graph, double p_malicious, double p_txDistribution, int numRounds) {
        this.p_graph = p_graph;
        this.p_malicious = p_malicious;
        this.p_tXDistribution = p_txDistribution;
        this.numRounds = numRounds;
    }

    public void setFollowees(boolean[] followees) {
        this.followees = followees;
        this.blacklists = new boolean[followees.length];
    }

    public void setPendingTransaction(Set<Transaction> pendingTransactions) {
        this.pendingTransactions = pendingTransactions;
    }

    public Set<Transaction> sendToFollowers() {
        Set<Transaction> sendingList = new HashSet<>(pendingTransactions);
        pendingTransactions.clear();
        return sendingList;
    }

    public void receiveFromFollowees(Set<Candidate> candidates) {
        Set<Integer> senders = candidates.stream().map(c -> c.sender).collect(toSet());
        for (int i=0; i<followees.length; i++) {
            if (followees[i] && !senders.contains(i)) {
                blacklists[i] = true;
            }
        }
        for (Candidate c : candidates) {
            if (!blacklists[c.sender]) {
                pendingTransactions.add(c.tx);
            }
        }
    }
}
