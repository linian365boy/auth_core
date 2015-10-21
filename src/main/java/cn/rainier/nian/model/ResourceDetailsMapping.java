package cn.rainier.nian.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.jdbc.object.MappingSqlQuery;

public class ResourceDetailsMapping extends MappingSqlQuery<Resource> {
	
    protected ResourceDetailsMapping(DataSource dataSource,
        String resourceQuery) {
        super(dataSource, resourceQuery);
        compile();
    }

    protected Resource mapRow(ResultSet rs, int rownum) throws SQLException {
		Resource res = new Resource();
		res.setRes_string(rs.getString("res_string"));
		res.setRuName(rs.getString("name"));
        return res;
    }

}
