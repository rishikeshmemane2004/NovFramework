package rough.pojoPractice;

public class Links {

	private Self self;
	private Edit edit;
	private Avatar avatar;
	
	public Links(Self sf, Edit ed, Avatar av) {
		super();
		this.self = sf;
		this.edit = ed;
		this.avatar = av;
	}

	public Self getSelf() {
		return self;
	}

	public void setSelf(Self self) {
		this.self = self;
	}

	public Edit getEdit() {
		return edit;
	}

	public void setEdit(Edit edit) {
		this.edit = edit;
	}

	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

	 
	
	
}
