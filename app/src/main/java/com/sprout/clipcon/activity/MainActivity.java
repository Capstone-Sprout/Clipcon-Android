package com.sprout.clipcon.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.sprout.clipcon.R;
import com.sprout.clipcon.model.Message;
import com.sprout.clipcon.server.Endpoint;
import com.sprout.clipcon.server.EndpointInBackGround;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.websocket.DeploymentException;
import javax.websocket.EncodeException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 연결
        Button createBtn = (Button) findViewById(R.id.main_create);
        Button joinBtn = (Button) findViewById(R.id.main_join);

        // create group
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("************  테스트중 33 **************");
                showCreateDialog();
                System.out.println("************  테스트중 44 **************");
            }
        });

        // join group
        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showJoinDialog();
            }
        });

        System.out.println("************  테스트중 55 **************");

        new EndpointInBackGround().execute("connect");

        System.out.println("************  테스트중 66 **************");
    }

    public void showCreateDialog() {
        new MaterialDialog.Builder(this)
                .title("그룹명을 입력하세요")
                .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME)
                .positiveText("생성")
                .input("Group 1", "Group 1", false, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        Toast.makeText(getApplicationContext(), "만들어진 그룹명은 " + input.toString() + " 입니다", Toast.LENGTH_SHORT).show();
                        System.out.println("************  테스트중 11 **************");

                        final EndpointInBackGround.ResultCallback result = new EndpointInBackGround.ResultCallback() {

                            @Override
                            public void onSuccess() {
                                System.out.println("1차 콜백 성공");
                                startActivity(new Intent(getApplicationContext(), GroupActivity.class));
                            }
                        };

                        new EndpointInBackGround(result).execute("request_create_group");

                    }
                }).show();

        System.out.println("************  테스트중 22 **************");
    }

    public void showJoinDialog() {
        new MaterialDialog.Builder(this)
                .title("고유키를 입력하세요")
                .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME)
                .positiveText("참여")
                .input("", "", false, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        Toast.makeText(getApplicationContext(), "고유키는 " + input.toString() + " 입니다", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), GroupActivity.class));
                    }
                }).show();
    }
}
