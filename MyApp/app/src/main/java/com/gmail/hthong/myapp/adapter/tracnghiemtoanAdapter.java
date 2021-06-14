package com.gmail.hthong.myapp.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.hthong.myapp.DAO.quizcontractDao;
import com.gmail.hthong.myapp.R;
import com.gmail.hthong.myapp.model.QuizContract;

import java.util.List;

public class tracnghiemtoanAdapter extends RecyclerView.Adapter<tracnghiemtoanAdapter.ViewHolder> {

    private List<QuizContract> listQuiz;
    private Context context;
    com.gmail.hthong.myapp.DAO.quizcontractDao quizcontractDao;

    public tracnghiemtoanAdapter(List<QuizContract> listQuiz, Context context) {
        this.listQuiz = listQuiz;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.list_quiz, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuizContract quiz = listQuiz.get(position);
        int i = position + 1;
        holder.txtstt.setText(String.valueOf(i));
        holder.txtques.setText(quiz.getQuestion());
        holder.btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_delete(quiz);

            }
        });
        holder.btndetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quiz.getCategory() == 1) {

                    // bundle để truyền dữ liệu giữa các intent
                    Intent intent = new Intent(view.getContext(), com.gmail.hthong.myapp.detailTracNghiemToan.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", quiz.getId());
                    bundle.putString("question", quiz.getQuestion());
                    bundle.putString("option1", quiz.getOption1());
                    bundle.putString("option2", quiz.getOption2());
                    bundle.putString("option3", quiz.getOption3());
                    bundle.putString("answer", quiz.getAnswer());
                    intent.putExtra("DATA", bundle);
                    view.getContext().startActivity(intent);
                } else {
                    Intent intent = new Intent(view.getContext(), com.gmail.hthong.myapp.detailTracNghiemVan.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", quiz.getId());
                    bundle.putString("question", quiz.getQuestion());
                    bundle.putString("option1", quiz.getOption1());
                    bundle.putString("option2", quiz.getOption2());
                    bundle.putString("option3", quiz.getOption3());
                    bundle.putString("answer", quiz.getAnswer());
                    intent.putExtra("DATA", bundle);
                    view.getContext().startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return listQuiz.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtstt, txtques;
        ImageView btndetails, btndel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtstt = itemView.findViewById(R.id.txtstt);
            txtques = itemView.findViewById(R.id.txtques);
            btndetails = itemView.findViewById(R.id.btndetails);
            btndel = itemView.findViewById(R.id.btndel);

        }
    }
    public void dialog_delete(QuizContract quiz) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.delete_dialog);
        dialog.show();
        Button btnok = (Button) dialog.findViewById(R.id.btnOk);
        Button btnHuy = dialog.findViewById(R.id.btnHuy);
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quizcontractDao=new quizcontractDao(context);
                try{
                    if (quizcontractDao.deleteQuiz(quiz)>0){
                        if (quiz.getCategory()==1){
                            Intent intent = new Intent(context, com.gmail.hthong.myapp.listTracNghiemToan.class);
                            context.startActivity(intent);
                        }else {
                            Intent intent = new Intent(context, com.gmail.hthong.myapp.listTracNghiemVan.class);
                            context.startActivity(intent);
                        }

                    }else {
                        Toast.makeText(context,"Có Lỗi xãy ra",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Log.e("loi",e.toString());
                }


            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        }
        );
    }
}