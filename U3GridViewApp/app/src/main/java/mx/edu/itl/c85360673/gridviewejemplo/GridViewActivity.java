package mx.edu.itl.c85360673.gridviewejemplo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import com.bumptech.glide.Glide;


public class GridViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{

    final Integer [] escudos = { R.drawable.eevee,            R.drawable.flareon,
                                 R.drawable.jolteon,          R.drawable.vaporeon,
                                 R.drawable.umbreon,          R.drawable.espeon,
                                 R.drawable.glaceon,          R.drawable.leafeon,
                                 R.drawable.sylveon};

    private GridView       grdvClubes;
    private GridAdaptador  adaptador;

    //------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView ( R.layout.activity_grid_view);

        grdvClubes = findViewById ( R.id.grdvClubes );
        adaptador = new GridAdaptador ( this, R.layout.gridview_item_imagen, escudos );
        grdvClubes.setAdapter ( adaptador );

        grdvClubes.setOnItemClickListener ( this );
    }

    //------------------------------------------------------------------------------------------

    @Override
    public void onItemClick ( AdapterView<?> adapterView, View view, int i, long l ) {
        Intent intent = new Intent( this, GridViewDetalleActivity.class );

        // Pasamos como argumento al otro activity el ID del recurso drawable del escudo seleccionado
        intent.putExtra ( "escudo", escudos [ i ] );
        startActivity ( intent );
    }

    //------------------------------------------------------------------------------------------

    public class GridAdaptador extends ArrayAdapter {
        private Integer [] escudos;
        private int        layoutResId;
        private LayoutInflater inflater;

        //------------------------------------------------------------------------------------------
        // Constructor

        public GridAdaptador(@NonNull Context context, int resource, @NonNull Integer [] escudos ) {
            // Llamar al constructor de la clase padre y guardar los argumentos en variables de la clase
            super ( context, resource, escudos );
            this.escudos = escudos;
            layoutResId = resource;
            inflater = LayoutInflater.from ( context );
        }

        //------------------------------------------------------------------------------------------

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if ( convertView == null ) {
                convertView = inflater.inflate ( layoutResId, parent, false );
            }

            ImageView imgEscudo = convertView.findViewById ( R.id.imgvEscudo );
            imgEscudo.setScaleType ( ImageView.ScaleType.FIT_XY );

            //imgEscudo.setImageResource ( escudos [ position] );
            Glide.with ( imgEscudo.getContext() ).load ( escudos [ position ] ).into ( imgEscudo );

            return convertView;
        }

        //------------------------------------------------------------------------------------------
    }
}
