package prototype.support;

public class Cooldown {
    double cooldown, cooldownReset, delay, delayReset;

    public Cooldown(long cooldown, long delay) {
        this.cooldownReset = this.cooldown = cooldown;
        this.delayReset = this.delay = delay;
    }

    public void reset() {
        this.delay = this.delayReset;
        this.cooldown = this.cooldownReset;
    }

    public boolean trigger() {
        if (delay <= 0 && cooldown <= 0) {
            cooldown = cooldownReset;
            delay = delayReset;
            return true;
        }
        return false;
    }

    public void updateWait(long now) {
        if (delay <= 0) {
            cooldown--;
        }
        delay--;
    }

    public boolean updateTrigger(long now) {
        if (delay <= 0) {
            if (cooldown <= 0) {
                cooldown = cooldownReset;
                return true;
            } else {
                cooldown--;
                return false;
            }
        }
        delay--;
        return false;
    }
}
