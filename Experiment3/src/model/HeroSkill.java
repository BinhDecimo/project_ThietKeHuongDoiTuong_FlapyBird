package model;

public class HeroSkill implements Skill {
	
	private boolean active;
    private Hero hero;
    private static final int TELEPORT_DISTANCE = 70; // Distance to move past pipes

    public HeroSkill() {
        this.active = false;
    }

	@Override
	public void skill() {
		if (!active) {
			active = true;
			hero.setX(hero.getX() + TELEPORT_DISTANCE); // Move the character
			// Reset active to false immediately after the skill is used
			active = false;
		}
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}
}
