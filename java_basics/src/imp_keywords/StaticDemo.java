package imp_keywords;

public class StaticDemo {

	private StaticDemo(){
		
	}
	
	private static class SingletonHolder {
        public static final StaticDemo instance = new StaticDemo();
    }

    public static StaticDemo getInstance() {
        return SingletonHolder.instance;
    }
	
	public static void main(String[] args) {
          getInstance();
	}
}
