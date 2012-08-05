
package net.gabuchan.androidrecipe.recipe057;

import java.io.Serializable;

public class ListItem implements Serializable {
    private static final long serialVersionUID = 1L;

    int resId; // アイコン画像リソースID
    String name; // ユーザ名
    String comment; // コメント

    public ListItem(int resId, String name, String comment) {
        this.resId = resId;
        this.name = name;
        this.comment = comment;
    }
}
