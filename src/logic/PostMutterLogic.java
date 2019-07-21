package logic;

public class PostMutterLogic {

	public void execute(Mutter mutter) {
		MutterDAO dao = new MutterDAO();
		dao.insert(mutter);
	}

}