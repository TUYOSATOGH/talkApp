package logic;

public class DeleteMutterLogic {

	public void execute(Mutter mutter) {
		MutterDAO dao = new MutterDAO();
		dao.delete(mutter);
	}

}