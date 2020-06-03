package com.riskm.androidclient.ui.admin.ui.log;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import com.riskm.androidclient.R;
import com.riskm.androidclient.entity.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LogFragment extends ListFragment {
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_log,container,false);
        listView = root.findViewById(android.R.id.list);
        creataTable();
        return  root;
    }

    private void creataTable(){
        ArrayList<Log> logList;
        String[] names = {"消息中心","家教记录","设置"};
        ArrayAdapter viewDat = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
        for (String name :
                names) {
            viewDat.add(name);
        }

        listView.setAdapter(viewDat);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                StudentMapper studentMapper = new StudentMapper();
////                Adapter test = parent.getAdapter();
//                System.out.println(studentList.get(position).toString());
//                final AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(SelectActivity.this);
//                alterDiaglog.setIcon(R.drawable.icon);//图标
//                alterDiaglog.setTitle(studentList.get(position).getName());//文字
//                alterDiaglog.setMessage(studentList.get(position).toString());
//                alterDiaglog.setPositiveButton("只是看看", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        Toast.makeText(SelectActivity.this, "no action", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                alterDiaglog.setNegativeButton("删除", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // StudentMapper.deleteStudent();
//                        new StudentMapper().deleteStudent(studentList.get(position).getId());
//                        studentList = studentMapper.getStudentList();
//                        CreateTable();
//                        Toast.makeText(SelectActivity.this, "delete", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                alterDiaglog.setNeutralButton("修改", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Intent intent = new Intent();
//                        StudentMapper.setSessionInf(studentList.get(position));
//                        intent.setClass(SelectActivity.this, InsertActivity.class);
//                        SelectActivity.this.startActivity(intent);
//                        Toast.makeText(SelectActivity.this, "repair", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                //显示
//                alterDiaglog.show();
            }
        });
    }
}
