import java.sql.SQLException;
import java.util.List;

import com.hms.doa.HotelDoa;
import com.hms.model.Hotel;

public class Main {

	public static void main(String[] args) {

		HotelDoa doa = new HotelDoa();
			Hotel hotel = new Hotel();
			hotel.setHotelName("High");
			hotel.setHotelLocation("bombay");
			hotel.setHotelAmenities("dsf");
			hotel.setHotelId(1);
		try {
			doa.updateHotel(hotel);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
