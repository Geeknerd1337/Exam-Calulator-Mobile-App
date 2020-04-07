package com.myapp.whaticanmiss;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class SecondFragment extends Fragment {

    private float myResult;
    private TextView resultText;
    private TextView questionResult;
    private EditText questionValue;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View fragmentSecondLayout = inflater.inflate(R.layout.fragment_second, container, false);
        resultText = fragmentSecondLayout.findViewById(R.id.resultText);
        questionValue = fragmentSecondLayout.findViewById(R.id.questionField);
        questionResult = fragmentSecondLayout.findViewById(R.id.secondResult);

        // Inflate the layout for this fragment
        return fragmentSecondLayout;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SecondFragmentArgs args = SecondFragmentArgs.fromBundle(getArguments());
        final Float minPoints = args.getFirstFragResult();
        Float maxMissed = args.getSecondFragResult();
        final Float neededPoints = args.getThirdFragResult();
        String s = "";
        if(minPoints < 0){
            s = "You can miss the test and get the desired grade.";
        }else{
            s = "You can miss " + maxMissed.toString() + " points to get your desired grade.";
        }

        resultText.setText(s);

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        view.findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!questionValue.getText().toString().isEmpty()){
                    float numQuestions = Float.parseFloat(questionValue.getText().toString());
                    int maxMissedQuestions = (int) Math.floor((double)((minPoints/neededPoints)*numQuestions));
                    questionResult.setText("You can miss " + Integer.toString((int)numQuestions - maxMissedQuestions) + " questions and get the desired grade.");
                }
            }
        });
    }


}
