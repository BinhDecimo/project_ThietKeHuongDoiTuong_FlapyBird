package model;

public class PlaneSkill implements Skill {
    private boolean active;
    private long startTime;
	private static final int DURATION = 3000; // 3 seconds
    private Plane plane;

    public PlaneSkill(Plane plane) {
        this.plane = plane;
        this.active = false;
    }

    @Override
    public void skill() {
    	if (!active) {
			active = true;
			startTime = System.currentTimeMillis();
		}

		long currentTime = System.currentTimeMillis();
		if (currentTime - startTime < DURATION) {
			plane.setVelocityY(0); // Plane bay thẳng
//			plane.setGravity(0);
		} else {
			active = false;
//			plane.setGravity(1);
			plane.setVelocityY(plane.getGravity()); // Restore gravity effect
			
		}
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }
}
