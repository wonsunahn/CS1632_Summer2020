public interface RentACat {
	public boolean returnCat(int id);
	public boolean rentCat(int id);
	public String listCats();
	public boolean catExists(int id);
	public boolean catAvailable(int id);
	public Cat getCat(int id);
	public void addCat(Cat c);
}
