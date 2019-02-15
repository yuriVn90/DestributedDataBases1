package DBTranslate.layout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import DBTranslate.logic.TablesService;

@RestController
public class DBTranslateAPI {
	
	private TablesService tables;
	
	@Autowired
	public void setTables(TablesService tables) {
		this.tables = tables;
	}
	
	
	
}
