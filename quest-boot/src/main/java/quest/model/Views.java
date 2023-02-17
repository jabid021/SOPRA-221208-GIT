package quest.model;

public class Views {
	public static class ViewBase {
	}

	public static class ViewClient extends ViewBase {
	}
	
	public static class ViewClientWithFilieres extends ViewClient {
	}
	
	public static class ViewFiliere extends ViewBase {
	}

	public static class ViewFiliereWithStagiaires extends ViewFiliere {
	}

	public static class ViewFormateur extends ViewBase {
	}
	
	public static class ViewMatiere extends ViewBase {
	}

	public static class ViewOrdinateur extends ViewBase {
	}

	public static class ViewPersonne extends ViewBase {
	}
	
	public static class ViewSalle extends ViewBase {
	}

	public static class ViewStagiaire extends ViewPersonne {
	}

	public static class ViewStagiaireWithFilieres extends ViewStagiaire {
	}
}
