public class Player {
    String playerName;
    Tile[] playerTiles;
    int numberOfTiles;

    public Player(String name) {
        setName(name);
        playerTiles = new Tile[15]; // there are at most 15 tiles a player owns at any time
        numberOfTiles = 0; // currently this player owns 0 tiles, will pick tiles at the beggining of the game
    }

    /*
     * TODO: checks this player's hand to determine if this player is winning
     * the player with a complete chain of 14 consecutive numbers wins the game
     * note that the player whose turn is now draws one extra tile to have 15 tiles in hand,
     * and the extra tile does not disturb the longest chain and therefore the winning condition
     * check the assigment text for more details on winning condition
     */
    public boolean checkWinning() {
        int maxLength = findLongestChain();
        if( maxLength >= 14)
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
    /*
     * TODO: used for finding the longest chain in this player hand
     * this method should iterate over playerTiles to find the longest chain
     * of consecutive numbers, used for checking the winning condition
     * and also for determining the winner if tile stack has no tiles
     */
    public int findLongestChain() {
        int longestChain = 0;
        int currentChain = 0;
        for (int i = 0; i < playerTiles.length; i++)
        {
            
            if (playerTiles[i].getValue() == playerTiles[i - 1].getValue() + 1) 
            {
                currentChain++; 
                if (currentChain > longestChain) 
                {
                    longestChain = currentChain; 
                }
            } 
            else 
            {
                currentChain = 1; // Reset the current chain length
            }
        }
        return longestChain;
    }

    /*
     * TODO: removes and returns the tile in given index position
     */
    public Tile getAndRemoveTile(int index) {
        if ( this.numberOfTiles > index )
        {
            Tile result = this.playerTiles[index];
            for ( int i = index + 1; i < this.numberOfTiles; i++ )
            {
                this.playerTiles[i - 1] = this.playerTiles[i];
            }
            return result;
        }
        return null;
    }

    /*
     * TODO: adds the given tile to this player's hand keeping the ascending order
     * this requires you to loop over the existing tiles to find the correct position,
     * then shift the remaining tiles to the right by one
     */
    public void addTile(Tile t) {
        if ( this.numberOfTiles < 15 )
        {
            this.playerTiles[numberOfTiles] = t;
            this.numberOfTiles++;
        }
        else
        {
            System.out.println( "You can't have more than 15 tiles.");
        }
    }

    /*
     * finds the index for a given tile in this player's hand
     */
    public int findPositionOfTile(Tile t) {
        int tilePosition = -1;
        for (int i = 0; i < numberOfTiles; i++) {
            if(playerTiles[i].matchingTiles(t)) {
                tilePosition = i;
            }
        }
        return tilePosition;
    }

    /*
     * displays the tiles of this player
     */
    public void displayTiles() {
        System.out.println(playerName + "'s Tiles:");
        for (int i = 0; i < numberOfTiles; i++) {
            System.out.print(playerTiles[i].toString() + " ");
        }
        System.out.println();
    }

    public Tile[] getTiles() {
        return playerTiles;
    }

    public void setName(String name) {
        playerName = name;
    }

    public String getName() {
        return playerName;
    }
}