package listener;

import com.e.demoaplplication.bean.PostModel;

public interface FavClickListener {
       void onFavClick(PostModel model);
       void onRemove(PostModel model);
   }
