package com.basesoft.baseappretro.Helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.basesoft.baseappretro.R;

public class DialogHelper {

	// ============================
	// Basic Dialog Generators
	// ============================
	private static AlertDialog.Builder BaseDialog(Context context){
		AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.DialogStyle);
		return builder;
	}

	public static void showNativeDialog(Context context,String title, String message, Drawable icon, boolean isCancelable,
										String possitiveButtonText,DialogInterface.OnClickListener possitiveBehavior,
										String negativeButtonText,DialogInterface.OnClickListener negativeBehavior,
										String neutralButtonText,DialogInterface.OnClickListener neutralBehavior){
		AlertDialog.Builder builder = BaseDialog(context);
		builder.setTitle(title).setMessage(message).setIcon(icon)
				.setPositiveButton(possitiveButtonText,possitiveBehavior)
				.setNegativeButton(negativeButtonText,negativeBehavior)
				.setNeutralButton(neutralButtonText,neutralBehavior)
				.setCancelable(isCancelable)
				.show();
	}

	private static AlertDialog.Builder CreateBaseCustomDialog(Context context, View addView,
															  String possitiveButtonText, DialogInterface.OnClickListener possitiveBehavior,
															  String negativeButtonText, DialogInterface.OnClickListener negativeBehavior,
															  String neutralButtonText, DialogInterface.OnClickListener neutralBehavior){
		AlertDialog.Builder builder = BaseDialog(context);
		if(addView != null){builder.setView(addView);}
		if(possitiveButtonText != null && !possitiveButtonText.equals("")){builder.setPositiveButton(possitiveButtonText, possitiveBehavior);}
		if(negativeButtonText != null && !negativeButtonText.equals("")){builder.setNegativeButton(negativeButtonText, negativeBehavior);}
		if(neutralButtonText != null && !neutralButtonText.equals("")){builder.setNeutralButton(neutralButtonText, neutralBehavior);}
		return builder;
	}

	// ============================
	// Error Dialogs
	// ============================
    public static void showCommonErrorDialog(Context context,
                                             @Nullable String message, boolean isCancelable,
                                             @Nullable DialogInterface.OnClickListener onClickListener) {

		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View view = inflater.inflate(R.layout.dialog_error_base, null);

	    TextView textView = (TextView)view.findViewById(R.id.dialog_error_message);

	    textView.setText((message != null ? message : context.getResources().getString(R.string.common_failure_text)));

		CreateBaseCustomDialog(context, view, context.getResources().getString(R.string.ok),
			(onClickListener != null ? onClickListener : null),null,null,null,null).setCancelable(isCancelable).show();
    }


    public static void showCommonErrorDialog(Context context, String message, String title, boolean isCancelable,
                                             DialogInterface.OnClickListener onClickListener,
                                             @Nullable String pid){

		showNativeDialog(context, message, title, ContextCompat.getDrawable(context, R.drawable.modal_icon_alert), isCancelable, context.getResources().getString(R.string.ok), onClickListener, null, null, null, null);
    }

	// ============================
	// Common message dialog
	// ============================
	public static void showCommonMessageDialog(final Context context, String title,String message, Drawable icon , boolean isCancelable, String button1Text, DialogInterface.OnClickListener btn1Behavior,
											   String button2Text, DialogInterface.OnClickListener btn2Behavior, String button3Text, DialogInterface.OnClickListener btn3Behavior){
		showNativeDialog(context,title,message,icon,isCancelable,button1Text,btn1Behavior,button2Text,btn2Behavior,button3Text,btn3Behavior);
	}
	public static void showCommonMessageDialog(final Context context, String message, Drawable icon , boolean isCancelable, String button1Text, DialogInterface.OnClickListener btn1Behavior,
											   String button2Text, DialogInterface.OnClickListener btn2Behavior, String button3Text, DialogInterface.OnClickListener btn3Behavior){
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.dialog_common_message, null);
		((TextView)view.findViewById(R.id.message)).setText(message);
		if(icon != null){
			((ImageView)view.findViewById(R.id.icon)).setImageDrawable(icon);
			view.findViewById(R.id.icon).setVisibility(View.VISIBLE);
		}
		CreateBaseCustomDialog(context,view,button1Text,btn1Behavior,button2Text,btn2Behavior,button3Text,btn3Behavior).setCancelable(isCancelable).show();
	}

	// ============================
	// Confirmation Dialogs
	// ============================
	public static void showConfirmationDialog(Context context, String message, DialogInterface.OnClickListener btn1Behavior) {
		showCommonMessageDialog(context, message, ContextCompat.getDrawable(context, R.drawable.modal_icon_alert), false, context.getString(R.string.ok), btn1Behavior, context.getString(R.string.cancel_button), null, null, null);
	}

	// ============================
	// Information Dialog
	// ============================
	public static void showInformationDialog(Context context,String title,String message){
		showNativeDialog(context,title,message,null,true,context.getString(R.string.ok),null,null,null,null,null);
	}

	// ============================
	// Generic progress dialog with 1 message
	// ============================
	public static ProgressDialog generateProgressDialogWithOneMessage(Context context,String msg) {
		ProgressDialog progressDialog = new ProgressDialog(context);
		if (msg != null){
			progressDialog.setMessage(msg);
		} else {
			progressDialog.setMessage(context.getString(R.string.progress_bar_generic_message));
		}
		progressDialog.setCancelable(false);
		return progressDialog;
	}

}

