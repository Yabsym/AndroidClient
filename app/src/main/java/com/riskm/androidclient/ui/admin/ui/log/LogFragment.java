package com.riskm.androidclient.ui.admin.ui.log;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.alibaba.fastjson.JSON;
import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.style.FontStyle;
import com.bin.david.form.data.table.TableData;
import com.riskm.androidclient.R;
import com.riskm.androidclient.entity.Log;
import com.riskm.androidclient.ui.admin.AdminActivity;
import com.riskm.androidclient.ui.login.LoginActivity;
import com.riskm.androidclient.util.CallBackUtil;
import com.riskm.androidclient.util.RealResponse;
import com.riskm.androidclient.util.UrlHttpUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class LogFragment extends Fragment {
    private SmartTable tableLog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_log, container, false);
        tableLog = root.findViewById(R.id.tableLog);
        creataTable();
        return root;
    }

    private void creataTable() {

        UrlHttpUtil.post("http://10.0.3.2:8080/admin/AndroidGetLogDat", null, null, new CallBackUtil.CallBackDefault() {
            @Override
            public void onFailure(int code, String errorMessage) {

            }

            @Override
            public void onResponse(RealResponse response) {
                BufferedReader reader;
                StringBuffer sbf = new StringBuffer();
                try {
                    String strRead = null;
                    reader = new BufferedReader(new InputStreamReader(response.inputStream, StandardCharsets.UTF_8));
                    while ((strRead = reader.readLine()) != null) {
                        sbf.append(strRead);
                        sbf.append("\r\n");
                    }
                    reader.close();
                    Map map = JSON.parseObject(sbf.toString());
                    String dat = map.get("logDat").toString();
                    //TODO what?

                } catch (Exception e) {
                    e.printStackTrace();
                }

                List<Log> logDat = new ArrayList<>();

                Column<Integer> logID = new Column<>("日志流水号", "logID");
                Column<String> context = new Column<>("操作内容", "context");
                Column<String> operator = new Column<>("操作人", "operator");
                Column<Date> time = new Column<>("时间", "time");
                Column<String> type = new Column<>("类型", "type");
                TableData<Log> tableData = new TableData<>("日志表", logDat, logID, context, operator, time, type);

                tableLog.setTableData(tableData);
                tableLog.getConfig().setContentStyle(new FontStyle(50, Color.RED));
            }
        });


//
//
//
//        ArrayList<Log> logList;
//        String[] names = {"消息中心","家教记录","设置"};
//        ArrayAdapter viewDat = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
//        for (String name :
//                names) {
//            viewDat.add(name);
//        }
//
//        listView.setAdapter(viewDat);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
//            }
//        });
    }
}
