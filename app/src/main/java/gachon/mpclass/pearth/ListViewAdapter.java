package gachon.mpclass.pearth;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.time.Instant;
import java.time.LocalDate;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListViewAdapter extends BaseAdapter {
    int cnt = 0;
    Context context;
    ArrayList<ListViewItem> data;
    Uri profileUri = null;
    Uri defaultUri = null;
    String userProfile = null;
    int check = 0;
    String n;
    DatabaseReference mDB = FirebaseDatabase.getInstance().getReference().child("report");
    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("board");
    FirebaseStorage storage = FirebaseStorage.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    ListViewAdapter(Context context) {
        this.context = context;
    }

    public ListViewAdapter(ArrayList<ListViewItem> data) {
        this.data = data;


    }

    public void clear() {
        data.clear();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void showButton(ImageButton btn) {
        btn.setVisibility(View.VISIBLE);
    }

    public void hideButton(ImageButton btn) {
        btn.setVisibility(View.INVISIBLE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ListViewItem list = data.get(position);
        convertView = inflater.inflate(R.layout.boradlist, parent, false);
        TextView textView1 = (TextView) convertView.findViewById(R.id.textView1);
        TextView textView2 = (TextView) convertView.findViewById(R.id.textView2);
        TextView tag = (TextView) convertView.findViewById(R.id.Tag);
        Button good = (Button) convertView.findViewById(R.id.good);
        Button report = (Button) convertView.findViewById(R.id.report);
        ImageButton delete = (ImageButton) convertView.findViewById(R.id.delete);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_preview);
        CircleImageView circleImageView = (CircleImageView) convertView.findViewById(R.id.profileImage);
        String Uid = user.getUid(); //현재사용자
        String profile = "profile/" + list.getTitle() + ".png"; //글쓴 사용자의 프로필 사진
        String post = list.getUid(); //글쓴사용자
        good.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "좋아요를 눌렀습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        report.setOnClickListener(new View.OnClickListener() {
            String ref = G.keyList.get(position); //클릭한 글의 고유주소

            //            String user = list.getUid();
            public void onClick(View v) {
                mDB.child("report" + cnt).child("postRef").setValue(ref);

//                mDB.child("report"+cnt).child("postUser").setValue(user);
                Toast.makeText(context, "신고되었습니다.", Toast.LENGTH_SHORT).show();

            }
        });

        if (Uid != null) {
            if (Uid.equals(post)) {
                showButton(delete);
                delete.setOnClickListener(new View.OnClickListener() {

                    StorageReference storageRef = storage.getReferenceFromUrl("gs://pearth-7ec20.appspot.com").child("images/" + list.getFileName());

                    @Override
                    public void onClick(View v) {
                        storageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                //사진 삭제 성공

                            }
                        });
                        dbRef.child(G.keyList.get(position)).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(context, "삭제 성공", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            } else {
                hideButton(delete);
            }
        }

        StorageReference profileRef = storage.getReference();

        profileRef.child(profile).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                profileUri = uri;
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
        StorageReference defaultRef = storage.getReference();
        profileRef.child("profile/plant.png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                defaultUri = uri;
            }
        });

        if (list.getImgUrl() == null)//사진이 있을때
        {
            textView1.setText(list.getTitle());
            textView2.setText(list.getContent());
            tag.setText(list.getTag());

            if (Uid != null) {
                if (Uid.equals(post)&&profileUri!=null) {
                    Glide.with(convertView)
                            .load(profileUri)
                            .into(circleImageView);
                } else {
                    Glide.with(convertView)
                            .load(defaultUri)
                            .into(circleImageView);

                }
            }


        } else {
            textView1.setText(list.getTitle());
            textView2.setText(list.getContent());
            tag.setText(list.getTag());
            Glide.with(convertView)
                    .load(list.getImgUrl())
                    .into(imageView);
            if (Uid != null) {
                if (Uid.equals(post)&&profileUri!=null) {
                    Glide.with(convertView)
                            .load(profileUri)
                            .into(circleImageView);
                } else {
                    Glide.with(convertView)
                            .load(defaultUri)
                            .into(circleImageView);

                }
            }
        }

        cnt++;
        return convertView;
    }
}