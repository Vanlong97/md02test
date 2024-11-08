package ra.entity;

import com.sun.xml.internal.ws.addressing.WsaActionUtil;
import ra.business.BookTypeBusiness;

import java.util.Scanner;

public class BookType implements IBookManagement {
    private int typeId;
    private String typeName;
    private String description;
    private Boolean isDelete = false;

    public BookType(int typeId, String typeName, String description, boolean isDelete) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.description = description;
        this.isDelete = isDelete;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void inputData(Scanner sc) {
        System.out.println("Nhap vao ten loai sach ");
        typeName = sc.nextLine();
        System.out.println("Nhap vao mo ta cho sach: ");
        description = sc.nextLine();
    }

    @Override
    public void displayData() {
        System.out.println("-------------------------------");
        System.out.println("Ten loai sach " + typeName);
        System.out.println("Ma loai sach " + typeId);
        System.out.println("Mo ta cho loai sach " + description);
        System.out.println("Trang thai xoa: " + isDelete);
        System.out.println("-------------------------------");
    }
}
