
package prototype.objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameObjectTest {

    @Test
    void constructor_initializesDefaultTeam() {
        GameObject object = new GameObject();

        assertEquals(GameObject.Team.WORLD, object.getTeam());
    }

    @Test
    void getTeam_returnsCurrentTeam() {
        GameObject object = new GameObject();

        assertEquals(GameObject.Team.WORLD, object.getTeam());
    }

    @Test
    void setTeam_changesTeam() {
        GameObject object = new GameObject();

        object.setTeam(GameObject.Team.PLAYER);

        assertEquals(GameObject.Team.PLAYER, object.getTeam());
    }

    @Test
    void setTeam_canChangeTeamMultipleTimes() {
        GameObject object = new GameObject();

        object.setTeam(GameObject.Team.PLAYER);
        assertEquals(GameObject.Team.PLAYER, object.getTeam());

        object.setTeam(GameObject.Team.ENEMY);
        assertEquals(GameObject.Team.ENEMY, object.getTeam());
    }

    @Test
    void constructor_initializesHealth() {
        GameObject object = new GameObject();

        assertEquals(100, object.currentHealth);
        assertEquals(100, object.maxHealth);
    }

    @Test
    void health_minmax() {
        GameObject object = new GameObject();

        object.takeDamage(50);
        assertEquals(50, object.currentHealth);
    }
}
